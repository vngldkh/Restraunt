package simulation;

import com.google.gson.Gson;
import deserializer.*;
import manager.DishCard;
import manager.MenuDish;
import process.Cooker;
import process.Equipment;
import process.OperationType;
import process.ProcessAgent;
import storage.Product;
import visitor.Visitor;
import visitor.VisitorAgent;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Simulation implements Runnable {
    private LocalDateTime currentDateTime;
    private final Restaurant restaurant;
    private final ExecutorService threadPool;
    private int subscribers;
    private int respondCount;
    public final Object monitor = new Object();
    private final Recorder recorder = new Recorder();

    public Simulation(LocalDateTime dateTime, ArrayList<Product> products,
                      ArrayList<Cooker> cookers, ArrayList<Equipment> equipment, ArrayList<OperationType> operations,
                      ArrayList<DishCard> dishCards, ArrayList<MenuDish> menuDishes, ArrayList<Visitor> visitors) {
        currentDateTime = dateTime;
        restaurant = new Restaurant(this, products, cookers, equipment, operations, dishCards, menuDishes, visitors);
        threadPool = Executors.newCachedThreadPool();
    }

    public LocalDateTime getCurrentTime() {
        return currentDateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void execute(Runnable task) {
        threadPool.execute(task);
    }

    public Future<?> submit(Runnable task) {
        return threadPool.submit(task);
    }

    public synchronized void subscribe() {
        ++subscribers;
    }

    public synchronized void unsubscribe() {
        --subscribers;
    }

    public synchronized void respond() {
        ++respondCount;
    }
    public synchronized void addProcess(ProcessAgent processAgent) {
        ArrayList<OperationID> operationIDS = new ArrayList<>();
        for (var operationAgent : processAgent.getUtilized()) {
            OperationElement operationSerialized = operationAgent.getSerialized();
            recorder.addOperation(operationSerialized);
            operationIDS.add(operationSerialized.getId());
        }
        ProcessElement process = new ProcessElement(processAgent.getId(), processAgent.getMenuDishId(),
                                                           processAgent.getStartTime(), processAgent.getFinishTime(),
                                                false, operationIDS );
        recorder.addProcess(process);

    }

    @Override
    public void run() {
        while (!restaurant.visitorAgents.isEmpty()) {
            for (var visitorAgent : restaurant.visitorAgents) {
                if (visitorAgent.startedOrder()) {
                    visitorAgent.makeOrder();
                }
            }
            synchronized (monitor) {
                do {
                    try {
                        monitor.wait(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (respondCount != subscribers);
                respondCount = 0;
                monitor.notifyAll();
                try {
                    monitor.wait(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            currentDateTime = currentDateTime.plusSeconds(1);
            restaurant.visitorAgents.removeIf(VisitorAgent::finished);
        }


        ProcessSerialized process = new ProcessSerialized(recorder.getProcesses());
        OperationSerialized operation = new OperationSerialized(recorder.getOperations());

        try (PrintWriter out = new PrintWriter(new FileWriter("process_log.txt"))) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(process);
            out.write(jsonString);
        } catch (Exception error) {
            error.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter("operation.txt"))) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(operation);
            out.write(jsonString);
        } catch (Exception error) {
            error.printStackTrace();
        }
        System.out.println("Логи успешно записаны!");
    }
}

