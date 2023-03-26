package manager;

import process.CookerAgent;
import process.EquipmentAgent;
import process.Manual;
import simulation.Simulation;
import storage.StorageAgent;
import visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Manager {
    Simulation simulation;
    CopyOnWriteArrayList<OrderAgent> orders = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<CookerAgent> availableCookers = new CopyOnWriteArrayList<>();

    public Manager(Simulation simulation) {
        this.simulation = simulation;
    }

    public Menu ProvideMenu() {
        return new Menu(simulation.getRestaurant().getMenu());
    }

    public Handbook ProvideHandBook() {
        return new Handbook(simulation.getRestaurant().getDishCards());
    }

    public Manual ProvideManual() {
        return new Manual(simulation.getRestaurant().getOperations());
    }

    public void AcceptOrder(VisitorAgent visitor, ArrayList<MenuDish> order) {
        orders.add(new OrderAgent(simulation, visitor, order));
    }

    public synchronized CookerAgent GetAvailableExecutor() {
        if (availableCookers.isEmpty()) {
            return null;
        }
        var executor = availableCookers.get(0);
        availableCookers.remove(0);
        executor.Engage();
        return executor;
    }

    public synchronized void FreeExecutor(CookerAgent cookerAgent) {
        cookerAgent.Free();
        availableCookers.add(cookerAgent);
    }

    public synchronized EquipmentAgent ReserveEquipment(int id) {
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
}
