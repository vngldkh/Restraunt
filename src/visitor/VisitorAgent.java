package visitor;

import manager.Manager;
import manager.Menu;
import manager.MenuDish;
import simulation.Simulation;

import java.util.ArrayList;

public class VisitorAgent {
    Simulation simulation;
    Visitor visitor;

    public VisitorAgent(Simulation simulation, Visitor visitor) {
        this.simulation = simulation;
        this.visitor = visitor;
    }
    void makeOrder() {
        Manager manager = simulation.getRestaurant().getManager();
        VisOrdDish[] wishedDishes = visitor.dishes();
        Menu menu = manager.ProvideMenu();
        ArrayList<MenuDish> chosen_dishes = new ArrayList<>();
        for (VisOrdDish wishedDish : wishedDishes) {
            if (menu.dishIsAvailable(wishedDish.menu_dish())) {
                chosen_dishes.add(menu.getDish(wishedDish.menu_dish()));
            }
        }
        manager.AcceptOrder(this, chosen_dishes);
    }
}
