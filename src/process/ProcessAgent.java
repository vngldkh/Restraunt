package process;

import manager.DishCard;
import simulation.Simulation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProcessAgent implements Runnable {
    long id;
    Simulation simulation;
    DishCard dishCard;
    CopyOnWriteArrayList<OperationAgent> operationAgents;
    int menuDishId;
    int currentAsyncPoint = 0;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    boolean done = false;

    public ProcessAgent(long id, Simulation simulation, DishCard dishCard, int menuDishId) {
        this.id = id;
        this.simulation = simulation;
        this.dishCard = dishCard;
        this.menuDishId = menuDishId;
    }

    @Override
    public void run() {
        timeStart = simulation.getCurrentTime();
        System.out.println("Процесс " + id + " начался: " + timeStart);
        operationAgents = new CopyOnWriteArrayList<>();
        if (dishCard == null) {
            return;
        }
        for (var operation : dishCard.operations()) {
            OperationAgent operationAgent = new OperationAgent(simulation.getRestaurant().getManager().getOperationId(),
                                                               simulation, operation, dishCard.card_id());
            operationAgents.add(operationAgent);
        }

        while (!operationAgents.isEmpty()) {
            for (var operationAgent : operationAgents) {
                if (operationAgent.getAsyncPoint() == currentAsyncPoint && !operationAgent.hasStarted()) {
                    simulation.execute(operationAgent);
                }
            }

            synchronized (simulation.monitor) {
                try {
                    simulation.monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            int started = 0;
            int completed = 0;
            for (var operationAgent : operationAgents) {
                if (operationAgent.getAsyncPoint() == currentAsyncPoint) {
                    ++started;
                }
                if (operationAgent.isDone()) {
                    ++completed;
                    operationAgents.remove(operationAgent);
                }
                if (operationAgent.isCancelled()) {
                    return;
                }
            }
            if (started == completed) {
                ++currentAsyncPoint;
            }
        }

        timeFinish = simulation.getCurrentTime();
        done = true;
        log();
        System.out.println("Процесс " + id + " окончился: " + timeFinish);
    }

    void log() {
        // TODO
    }
}
