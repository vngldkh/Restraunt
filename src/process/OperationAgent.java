package process;

import simulation.Simulation;
import storage.ProductTypeAgent;

import java.util.ArrayList;

public class OperationAgent implements Runnable {
    Simulation simulation;
    Operation operation;
    ArrayList<ProductTypeAgent> productTypeAgents = new ArrayList<>();

    OperationAgent(Simulation simulation, Operation operation) {
        this.simulation = simulation;
        this.operation = operation;
    }

    @Override
    public void run() {
        CookerAgent executor = simulation.getRestaurant().getManager().GetAvailableExecutor();
        if (executor == null) {
            return;
        }

        EquipmentAgent equipment = simulation.getRestaurant().getManager().ReserveEquipment(operation.equip_type());
        if (equipment == null) {
            simulation.getRestaurant().getManager().FreeExecutor(executor);
            return;
        }

        OperProduct[] operProducts = operation.oper_products();
        var storage = simulation.getRestaurant().getStorage();
        for (var operProduct : operProducts) {
            var productTypeAgent = storage.ReserveProduct(operProduct.prod_type(),
                                                                         operProduct.prod_quantity());
            if (!productTypeAgent.Reserved()) {
                // TODO: make a menu dish unavailable
                simulation.getRestaurant().getManager().FreeExecutor(executor);
                equipment.Free();
                for (var agent : productTypeAgents) {
                    agent.CancelReservation();
                }
                return;
            }
        }
    }
    /* TODO:
        -1. reserve_cooker-
        -2. reserve_equip-
        3. record_time start-end
     */
}
