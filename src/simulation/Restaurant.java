package simulation;

import manager.DishCard;
import manager.Manager;
import manager.MenuDish;
import process.Equipment;
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
    private ConcurrentHashMap<Integer, EquipmentAgent> equipments;
    private DishCard[] dishCards;
    private ConcurrentHashMap<Integer, MenuDish> menu;
    private StorageAgent storage;
    private Operation[] operations;

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
        return dishCards;
    }

    public Operation[] getOperations() {
        return operations;
    }

    public CookerAgent[] getCookers() {
        return cookers;
    }

    public ConcurrentHashMap<Integer, EquipmentAgent> getEquipments() {
        return equipments;
    }
}
