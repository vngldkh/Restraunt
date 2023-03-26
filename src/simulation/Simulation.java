package simulation;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public synchronized void Subscribe() {
        ++subscribers;
    }

    public synchronized void Unsubscribe() {
        --subscribers;
    }

    public synchronized void Respond() {
        ++respondCount;
    }

    public void run() {
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
    }
}

