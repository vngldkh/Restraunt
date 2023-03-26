package manager;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Menu {
    private final ConcurrentHashMap<Integer, MenuDish> menu;

    Menu(ConcurrentHashMap<Integer, MenuDish> menuDishes) {
        menu = menuDishes;
    }

    public static ConcurrentHashMap<Integer, MenuDish> makeMap(MenuDish[] dishes) {
        var menu = new ConcurrentHashMap<Integer, MenuDish>();
        for (var dish : dishes) {
            menu.put(dish.menu_dish_id(), dish);
        }
        return menu;
    }

    public boolean dishIsAvailable(int dishId) {
        return menu.containsKey(dishId) && menu.get(dishId).menu_dish_active();
    }

    public MenuDish getDish(int dishId) {
        return menu.get(dishId);
    }
}
