package visitor;

import manager.Manager;
import manager.MenuDish;
import simulation.Simulation;

public class VisitorAgent {
    Simulation simulation;
    Visitor visitor;

    public VisitorAgent(Simulation simulation, Visitor visitor) {
        this.simulation = simulation;
        this.visitor = visitor;
    }
    void makeOrder() {
//        Manager manager = simulation.getRestaurant().getManager();
//        MenuDish[] menu = manager.ProvideMenu();
//        VisOrdDish[] WishedDishes = visitor.dishes();
//        MenuDish[]
    }
}
