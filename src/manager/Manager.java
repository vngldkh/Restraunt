package manager;

import process.Manual;
import simulation.Simulation;
import visitor.VisitorAgent;

import java.util.ArrayList;

public class Manager {
    Simulation simulation;
    ArrayList<OrderAgent> orders;

    public Manager(Simulation simulation) {
        this.simulation = simulation;
    }

    public Menu ProvideMenu() {
        return new Menu(simulation.getRestaurant().getMenu());
    }

    public Handbook ProvideHandBook() {
        return new Handbook(simulation.getRestaurant().getDishCards());
    }

    public Manual ProvideManual() {
        return new Manual(simulation.getRestaurant().getOperations());
    }

    public void AcceptOrder(VisitorAgent visitor, ArrayList<MenuDish> order) {
        orders.add(new OrderAgent(simulation, visitor, order));
    }
}
