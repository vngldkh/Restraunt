package simulation;

import manager.DishCard;
import manager.Manager;
import manager.MenuDish;
import storage.StorageAgent;
import process.CookerAgent;
import process.EquipmentAgent;

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

    public Manager getManager() {
        return manager;
    }
}
