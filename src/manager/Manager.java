package manager;

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

    public void AcceptOrder(VisitorAgent visitor, ArrayList<MenuDish> order) {
        orders.add(new OrderAgent(simulation, visitor, order));
    }
}
