package simulation;

import manager.DishCard;
import manager.Manager;
import manager.MenuDish;
import storage.StorageAgent;
import process.CookerAgent;
import process.EquipmentAgent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Restaurant {
    private Simulation simulation;
    private Manager manager;
    private CookerAgent[] cookers;
    private EquipmentAgent[] equipments;
    DishCard[] dish_cards;
    ConcurrentHashMap<Integer, MenuDish> menu;
    StorageAgent storage;

    public ConcurrentHashMap<Integer, MenuDish> getMenu() {
        return menu;
    }

    public Manager getManager() {
        return manager;
    }

    public StorageAgent getStorage() {
        return storage;
    }
}
