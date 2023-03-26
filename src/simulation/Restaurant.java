package simulation;

import manager.DishCard;
import manager.Manager;
import manager.MenuDish;
import manager.OrderAgent;
import process.Operation;
import storage.StorageAgent;
import process.CookerAgent;
import process.EquipmentAgent;
import visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Restaurant {
    private Simulation simulation;
    private Manager manager;
    public CookerAgent[] cookers;
    private ConcurrentHashMap<Integer, EquipmentAgent> equipments;
    private DishCard[] dishCards;
    private ConcurrentHashMap<Integer, MenuDish> menu;
    private StorageAgent storage;
    private Operation[] operations;

    final BlockingDeque<OrderAgent> orders = new LinkedBlockingDeque<>();
    final ArrayList<VisitorAgent> visitorAgents = new ArrayList<>();

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

    public synchronized void newOrder(OrderAgent orderAgent) {
        orders.addLast(orderAgent);
    }

}
