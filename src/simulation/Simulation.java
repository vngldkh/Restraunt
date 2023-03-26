package simulation;

import visitor.VisitorAgent;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class Simulation {
    private LocalDateTime currentDateTime;
    private final Restaurant restaurant;
    private final ExecutorService threadPool;
    private int subscribers;
    private int respondCount;
    public final Object monitor = new Object();

    Simulation() {
        currentDateTime = LocalDateTime.now();
        restaurant = new Restaurant();
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
                        wait(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (respondCount != subscribers);
                respondCount = 0;
                currentDateTime = currentDateTime.plusSeconds(1);
            }
            restaurant.visitorAgents.removeIf(VisitorAgent::finished);
        }
    }
}

