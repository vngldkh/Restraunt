package simulation;

import cooker.Cooker;
import dish.card.DishCard;
import equipment.Equipment;
import equipment.EquipmentAgent;
import manager.Manager;
import dish.menu.MenuDish;
import order.OrderAgent;
import order.process.operation.OperationType;
import storage.product.Product;
import storage.StorageAgent;
import visitor.Visitor;
import visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Restaurant {
    private Manager manager;
    public ArrayList<Cooker> cookers;
    private final ConcurrentHashMap<Integer, ArrayList<EquipmentAgent>> equipments;
    private final ArrayList<DishCard> dishCards;
    private final ConcurrentHashMap<Integer, MenuDish> menu;
    private final StorageAgent storage;
    private final ArrayList<OperationType> operations;

    final BlockingDeque<OrderAgent> orders = new LinkedBlockingDeque<>();
    final ArrayList<VisitorAgent> visitorAgents = new ArrayList<>();

    public Restaurant (Simulation simulation, ArrayList<Product> products, ArrayList<Cooker> cookers,
                       ArrayList<Equipment> equipment, ArrayList<OperationType> operations,
                       ArrayList<DishCard> dishCards, ArrayList<MenuDish> menuDishes, ArrayList<Visitor> visitors) {
        this.operations = operations;
        this.dishCards = dishCards;

        menu = new ConcurrentHashMap<>();
        for (var dish : menuDishes) {
            menu.put(dish.getMenuDishId(), dish);
        }

        equipments = new ConcurrentHashMap<>();
        for (var item : equipment) {
            if (!equipments.containsKey(item.equip_type())) {
                equipments.put(item.equip_type(), new ArrayList<>());
            }
            equipments.get(item.equip_type()).add(new EquipmentAgent(item));
        }

        storage = new StorageAgent(simulation, products);

        this.cookers = cookers;

        this.manager = new Manager(simulation, cookers);

        for (var visitor : visitors) {
            this.visitorAgents.add(new VisitorAgent(simulation, visitor));
        }
    }

    public ConcurrentHashMap<Integer, MenuDish> getMenu() {
        return menu;
    }

    public Manager getManager() {
        return manager;
    }

    public StorageAgent getStorage() {
        return storage;
    }

    public ArrayList<DishCard> getDishCards() {
        return dishCards;
    }

    public ArrayList<OperationType> getOperations() {
        return operations;
    }

    public ConcurrentHashMap<Integer, ArrayList<EquipmentAgent>> getEquipments() {
        return equipments;
    }

    public synchronized void newOrder(OrderAgent orderAgent) {
        orders.addLast(orderAgent);
    }

}
