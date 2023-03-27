package order.process.operation;

import cooker.CookerAgent;
import equipment.EquipmentAgent;
import serialization.entities.OperationElement;
import simulation.Simulation;
import storage.product.ProductTypeAgent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OperationAgent implements Runnable {
    private final long id;
    private final long procId;
    private final long dishCardId;
    private Simulation simulation;
    private Operation operation;
    private ArrayList<ProductTypeAgent> productTypeAgents = new ArrayList<>();
    private int menuDishId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long secondsPassed = 0;
    private long secondsNeeded;
    private boolean started = false;
    private boolean done = false;
    private boolean cancelled = false;
    private int cookerId;
    private int equipmentId;

    public OperationAgent(long id, long procId, long dishCardId, Simulation simulation, Operation operation, int menuDishId) {
        this.id = id;
        this.procId = procId;
        this.dishCardId = dishCardId;
        this.simulation = simulation;
        this.operation = operation;
        this.menuDishId = menuDishId;
        startTime = simulation.getCurrentTime();
        secondsNeeded = (long) (operation.oper_time() * 60);
    }

    public int getAsyncPoint() {
        return operation.oper_async_point();
    }

    public boolean isDone() {
        return done;
    }

    public boolean hasStarted() {
        return started;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public OperationElement getSerialized() {
        return new OperationElement(id, procId, dishCardId, startTime.toString(),
                                       endTime.toString(), equipmentId, cookerId, false);
    }
    @Override
    public void run() {
        CookerAgent executor = simulation.getRestaurant().getManager().getAvailableExecutor();
        if (executor == null) {
            return;
        }

        EquipmentAgent equipment = simulation.getRestaurant().getManager().reserveEquipment(operation.equip_type());
        if (equipment == null) {
            simulation.getRestaurant().getManager().freeExecutor(executor);
            return;
        } else if (equipment.isNull()) {
            simulation.getRestaurant().getManager().makeMenuDishUnavailable(menuDishId);
            cancelled = true;
            return;
        }

        ArrayList<OperProduct> operProducts = operation.oper_products();
        var storage = simulation.getRestaurant().getStorage();
        cookerId = executor.getId();
        equipmentId = equipment.getId();
        for (var operProduct : operProducts) {
            var productTypeAgent = storage.ReserveProduct(operProduct.prod_type(),
                                                                         operProduct.prod_quantity());
            if (!productTypeAgent.Reserved()) {
                simulation.getRestaurant().getManager().makeMenuDishUnavailable(menuDishId);
                simulation.getRestaurant().getManager().freeExecutor(executor);
                equipment.Free();
                for (var agent : productTypeAgents) {
                    agent.CancelReservation();
                }
                cancelled = true;
                return;
            }
        }

        simulation.subscribe();
        started = true;
        System.out.println("Операция " + id + " началась: " + simulation.getCurrentTime());

        while (secondsPassed < secondsNeeded) {
            simulation.respond();

            synchronized (simulation.monitor) {
                try {
                    simulation.monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            ++secondsPassed;
        }

        System.out.println("Операция " + id + " закончилась: " + simulation.getCurrentTime());
        simulation.getRestaurant().getManager().freeExecutor(executor);
        equipment.Free();
        endTime = simulation.getCurrentTime();
        simulation.unsubscribe();
        done = true;
        log();
    }

    void log() {
        // TODO
    }
}
