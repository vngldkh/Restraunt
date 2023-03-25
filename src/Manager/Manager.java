package manager;

import visitor.VisOrdDish;
import simulation.Simulation;

import java.util.ArrayList;

public class Manager {
    Simulation simulation;
    ArrayList<OrderAgent> orders;

    public Manager(Simulation simulation) {
        this.simulation = simulation;
    }
    public MenuDish[] ProvideMenu() {
        return simulation.getRestaurant().getMenu();
    }
    public void NewOrder(VisOrdDish[] order) {
        for (VisOrdDish dish : order) {

        }
    }
}
