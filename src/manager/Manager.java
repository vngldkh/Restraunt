package manager;

import process.Cooker;
import process.CookerAgent;
import process.EquipmentAgent;
import process.Manual;
import simulation.Simulation;
import visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Manager {
    Simulation simulation;
    CopyOnWriteArrayList<CookerAgent> availableCookers = new CopyOnWriteArrayList<>();
    AtomicLong processId;
    AtomicLong operationId;

    public Manager(Simulation simulation, ArrayList<Cooker> cookers) {
        this.simulation = simulation;
        for (var cooker : cookers) {
            if (cooker.cookActive()) {
                availableCookers.add(new CookerAgent(cooker));
            }
        }
        processId = new AtomicLong(0);
        operationId = new AtomicLong(0);
    }

    public Menu provideMenu() {
        return new Menu(simulation.getRestaurant().getMenu());
    }

    public Handbook provideHandBook() {
        return new Handbook(simulation, simulation.getRestaurant().getDishCards());
    }

    public Manual provideManual() {
        return new Manual(simulation.getRestaurant().getOperations());
    }

    public OrderAgent acceptOrder(VisitorAgent visitor, ArrayList<MenuDish> order) {
        var orderAgent = new OrderAgent(simulation, visitor, order);
        simulation.getRestaurant().newOrder(orderAgent);
        simulation.execute(orderAgent);
        return orderAgent;
    }

    public synchronized CookerAgent getAvailableExecutor() {
        if (availableCookers.isEmpty()) {
            return null;
        }
        var executor = availableCookers.get(0);
        availableCookers.remove(0);
        executor.Engage();
        return executor;
    }

    public synchronized void freeExecutor(CookerAgent cookerAgent) {
        cookerAgent.Free();
        availableCookers.add(cookerAgent);
    }

    public synchronized EquipmentAgent reserveEquipment(int id) {
        var equipments = simulation.getRestaurant().getEquipments();
        if (!equipments.containsKey(id)) {
            return new EquipmentAgent(null);
        }
        var equipment = equipments.get(id);
        for (var item : equipment) {
            if (item.isAvailable()) {
                item.Use();
                return item;
            }
        }
        return null;
    }

    public void makeMenuDishUnavailable(int menuDishId) {
        var menu = simulation.getRestaurant().getMenu();
        if (menu.containsKey(menuDishId)) {
            menu.get(menuDishId).makeUnavailable();
        }
    }

    public synchronized long getProcessId() {
        return processId.incrementAndGet();
    }

    public synchronized long getOperationId() {
        return operationId.incrementAndGet();
    }
}
