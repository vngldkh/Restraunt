package manager;

import simulation.Simulation;

import java.util.ArrayList;
import java.util.HashMap;

public class Handbook {
    Simulation simulation;
    HashMap<Integer, DishCard> handbook = new HashMap<>();

    Handbook(Simulation simulation, ArrayList<DishCard> dishCards) {
        this.simulation = simulation;
        for (var dishCard : dishCards) {
            handbook.put(dishCard.card_id(), dishCard);
        }
    }

    DishCard getDishCardByMenuId(int menuId) {
        if (!this.simulation.getRestaurant().getMenu().containsKey(menuId)) {
            return null;
        }
        return handbook.get(this.simulation.getRestaurant().getMenu().get(menuId).getDishCardId());
    }
}
