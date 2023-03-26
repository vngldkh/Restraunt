package process;

import simulation.Simulation;
import storage.ProductTypeAgent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OperationAgent implements Runnable {
    private Simulation simulation;
    private Operation operation;
    private ArrayList<ProductTypeAgent> productTypeAgents = new ArrayList<>();
    private int menuDishId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long secondsPassed = 0;
    private boolean done = false;

    OperationAgent(Simulation simulation, Operation operation, int menuDishId) {
        this.simulation = simulation;
        this.operation = operation;
        this.menuDishId = menuDishId;
        startTime = simulation.getCurrentTime();
    }

    int getAsyncPoint() {
        return operation.oper_async_point();
    }

    boolean isDone() {
        return done;
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
        }

        OperProduct[] operProducts = operation.oper_products();
        var storage = simulation.getRestaurant().getStorage();
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
                return;
            }
        }

        simulation.subscribe();

        while (secondsPassed != ((long) operation.oper_time() * 60)) {
            simulation.respond();
            synchronized (simulation.monitor) {
                try {
                    wait(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            ++secondsPassed;
        }

        simulation.getRestaurant().getManager().freeExecutor(executor);
        equipment.Free();

        endTime = simulation.getCurrentTime();

        simulation.unsubscribe();
        done = true;
        Log();
    }

    void Log() {
        // TODO
    }
}
