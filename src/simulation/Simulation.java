package simulation;

import java.time.LocalDateTime;

public class Simulation {
    private final LocalDateTime currentDateTime;
    private final Restaurant restaurant;

    Simulation() {
        currentDateTime = LocalDateTime.now();
        restaurant = new Restaurant();
    }

    public LocalDateTime getCurrentTime() {
        return currentDateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}

