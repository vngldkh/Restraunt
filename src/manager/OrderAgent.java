package manager;

import process.ProcessAgent;
import simulation.Simulation;
import storage.StorageAgent;
import visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.concurrent.Future;


public class OrderAgent implements Runnable {
    Simulation simulation;
    visitor.VisitorAgent customer;
    ArrayList<MenuDish> orderedDishes;
    ArrayList<DishCard> dishCards;
    ArrayList<ProcessAgent> processAgents;

    OrderAgent(Simulation simulation, VisitorAgent customer, ArrayList<MenuDish> orderedDishes) {
        this.customer = customer;
        this.simulation = simulation;
        this.orderedDishes = orderedDishes;
    }

    @Override
    public void run() {
        dishCards = new ArrayList<>();
        Handbook handbook = simulation.getRestaurant().getManager().ProvideHandBook();
        processAgents = new ArrayList<>();
        for (var menuDish : orderedDishes) {
            var dishCard = handbook.getDishCard(menuDish.menu_dish_card());
            dishCards.add(dishCard);
            var process = new ProcessAgent(simulation, dishCard);
            processAgents.add(process);
            simulation.execute(process);
        }
    }
}
