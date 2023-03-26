package simulation;

import manager.DishCard;
import manager.Manager;
import manager.MenuDish;
import process.Operation;
import storage.StorageAgent;
import process.CookerAgent;
import process.EquipmentAgent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Restaurant {
    private Simulation simulation;
    private Manager manager;
    public CookerAgent[] cookers;
    public EquipmentAgent[] equipments;
    DishCard[] dish_cards;
    ConcurrentHashMap<Integer, MenuDish> menu;
    StorageAgent storage;
    Operation[] operations;

    public ConcurrentHashMap<Integer, MenuDish> getMenu() {
        return menu;
    }

    public Manager getManager() {
        return manager;
    }

    public StorageAgent getStorage() {
        return storage;
    }

    public DishCard[] getDishCards() {
        return dish_cards;
    }

    public Operation[] getOperations() {
        return operations;
    }
}
