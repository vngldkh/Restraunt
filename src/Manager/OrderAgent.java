package manager;

import simulation.Simulation;
import visitor.VisitorAgent;

import java.util.ArrayList;


public class OrderAgent {
    Simulation simulation;
    visitor.VisitorAgent customer;
    ArrayList<MenuDish> orderedDishes;

    OrderAgent(Simulation simulation, VisitorAgent customer, ArrayList<MenuDish> orderedDishes) {
        this.customer = customer;
        this.simulation = simulation;
        this.orderedDishes = orderedDishes;
    }
}
