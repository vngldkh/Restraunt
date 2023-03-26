package manager;

import process.CookerAgent;
import process.EquipmentAgent;
import process.Manual;
import simulation.Simulation;
import visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Manager {
    Simulation simulation;
    CopyOnWriteArrayList<CookerAgent> availableCookers = new CopyOnWriteArrayList<>();

    public Manager(Simulation simulation) {
        this.simulation = simulation;
    }

    public Menu provideMenu() {
        return new Menu(simulation.getRestaurant().getMenu());
    }

    public Handbook provideHandBook() {
        return new Handbook(simulation.getRestaurant().getDishCards());
    }

    public Manual provideManual() {
        return new Manual(simulation.getRestaurant().getOperations());
    }

    public OrderAgent acceptOrder(VisitorAgent visitor, ArrayList<MenuDish> order) {
        var orderAgent = new OrderAgent(simulation, visitor, order);
        simulation.getRestaurant().newOrder(orderAgent);
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
            return null;
        }
        var equipment = equipments.get(id);
        if (!equipment.isAvailable()) {
            return null;
        }
        equipment.Use();
        return equipment;
    }

    public void makeMenuDishUnavailable(int menuDishId) {
        var menu = simulation.getRestaurant().getMenu();
        if (menu.containsKey(menuDishId)) {
            menu.get(menuDishId).makeUnavailable();
        }
    }
}
