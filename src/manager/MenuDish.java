package manager;

public class MenuDish {
    int menu_dish_id;
    int menu_dish_card;
    int menu_dish_price;
    boolean menu_dish_active;

    public MenuDish(int menu_dish_id, int menu_dish_card,
                    int menu_dish_price, boolean menu_dish_active) {
        this.menu_dish_id = menu_dish_id;
        this.menu_dish_card = menu_dish_card;
        this.menu_dish_price = menu_dish_price;
        this.menu_dish_active = menu_dish_active;
    }

    public int getMenuDishId() {
        return menu_dish_id;
    }
    public int getDishCardId() {
        return menu_dish_card;
    }

    public boolean getMenuDishActive() {
        return menu_dish_active;
    }

    public void makeUnavailable() {
        menu_dish_active = false;
    }
}
