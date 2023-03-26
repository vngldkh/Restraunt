package manager;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Menu {
    private final HashMap<Integer, MenuDish> menu = new HashMap<>();

    Menu(CopyOnWriteArrayList<MenuDish> menuDishes) {
        for (MenuDish dish : menuDishes) {
            menu.put(dish.menu_dish_id(), dish);
        }
    }

    public boolean dishIsAvailable(int dishId) {
        return menu.containsKey(dishId) && menu.get(dishId).menu_dish_active();
    }

    public MenuDish getDish(int dishId) {
        return menu.get(dishId);
    }
}
