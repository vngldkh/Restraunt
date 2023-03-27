package dish.menu;

public class MenuDish {
    int menuDishId;
    int menuDishCard;
    int menuDishPrice;
    boolean menuDishActive;

    public MenuDish(int menuDishId, int menuDishCard,
                    int menuDishPrice, boolean menuDishActive) {
        this.menuDishId = menuDishId;
        this.menuDishCard = menuDishCard;
        this.menuDishPrice = menuDishPrice;
        this.menuDishActive = menuDishActive;
    }

    public int getMenuDishId() {
        return menuDishId;
    }
    public int getDishCardId() {
        return menuDishCard;
    }

    public boolean getMenuDishActive() {
        return menuDishActive;
    }

    public void makeUnavailable() {
        menuDishActive = false;
    }
}
