package simulation;

import Manager.DishCard;
import Manager.Manager;
import Manager.MenuDish;
import Storage.StorageAgent;
import Process.CookerAgent;
import Process.EquipmentAgent;

public class Restaurant {
    private Simulation simulation;
    private Manager manager;
    private CookerAgent[] cookers;
    private EquipmentAgent[] equipments;
    DishCard[] dish_cards;
    MenuDish[] menu;
    StorageAgent storage;

    public MenuDish[] getMenu() {
        return menu;
    }
}
