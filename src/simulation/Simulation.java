package simulation;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulation {
    private final LocalDateTime currentDateTime;
    private final Restaurant restaurant;
    private final ExecutorService threadPool;

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
}

