package visitor;

import manager.Manager;
import manager.Menu;
import manager.MenuDish;
import manager.OrderAgent;
import simulation.Simulation;

import java.util.ArrayList;

public class VisitorAgent {
    Simulation simulation;
    Visitor visitor;
    OrderAgent order;

    public VisitorAgent(Simulation simulation, Visitor visitor) {
        this.simulation = simulation;
        this.visitor = visitor;
    }
    public void makeOrder() {
        Manager manager = simulation.getRestaurant().getManager();
        ArrayList<VisOrdDish> wishedDishes = visitor.dishes();
        Menu menu = manager.provideMenu();
        ArrayList<MenuDish> chosen_dishes = new ArrayList<>();
        for (VisOrdDish wishedDish : wishedDishes) {
            if (menu.dishIsAvailable(wishedDish.menu_dish())) {
                chosen_dishes.add(menu.getDish(wishedDish.menu_dish()));
            }
        }
        order = manager.acceptOrder(this, chosen_dishes);
    }

    public boolean finished() {
        return order != null && order.isDone();
    }
    
    public boolean startedOrder() {
        return simulation.getCurrentTime().isEqual(visitor.started());
    }
}
