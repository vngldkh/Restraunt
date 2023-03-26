package manager;

import simulation.Simulation;
import storage.StorageAgent;
import visitor.VisitorAgent;

import java.util.ArrayList;


public class OrderAgent implements Runnable {
    Simulation simulation;
    visitor.VisitorAgent customer;
    ArrayList<MenuDish> orderedDishes;
    private static Handbook handbook;

    OrderAgent(Simulation simulation, VisitorAgent customer, ArrayList<MenuDish> orderedDishes) {
        this.customer = customer;
        this.simulation = simulation;
        this.orderedDishes = orderedDishes;
    }

    @Override
    public void run() {
        StorageAgent storage = simulation.getRestaurant().getStorage();

    }
}
