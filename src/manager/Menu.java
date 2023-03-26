package manager;

import java.util.concurrent.ConcurrentHashMap;

public class Menu {
    private final ConcurrentHashMap<Integer, MenuDish> menu;

    Menu(ConcurrentHashMap<Integer, MenuDish> menuDishes) {
        menu = menuDishes;
    }

    public static ConcurrentHashMap<Integer, MenuDish> makeMap(MenuDish[] dishes) {
        var menu = new ConcurrentHashMap<Integer, MenuDish>();
        for (var dish : dishes) {
            menu.put(dish.getMenuDishId(), dish);
        }
        return menu;
    }

    public boolean dishIsAvailable(int dishId) {
        return menu.containsKey(dishId) && menu.get(dishId).getMenuDishActive();
    }

    public MenuDish getDish(int dishId) {
        return menu.get(dishId);
    }
}
