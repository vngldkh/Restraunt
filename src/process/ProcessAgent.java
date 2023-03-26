package process;

import manager.DishCard;
import simulation.Simulation;

import java.util.ArrayList;

public class ProcessAgent implements Runnable {
    Simulation simulation;
    DishCard dishCard;
    ArrayList<OperationAgent> operationAgents;

    public ProcessAgent(Simulation simulation, DishCard dishCard) {
        this.simulation = simulation;
        this.dishCard = dishCard;
    }

    @Override
    public void run() {
        Manual manual = simulation.getRestaurant().getManager().ProvideManual();
        OperationType[] operationTypes = dishCard.operations();
        operationAgents = new ArrayList<>();
        for (var operationType : operationTypes) {
            Operation operation = manual.getOperation(operationType.oper_type_id());
            OperationAgent operationAgent = new OperationAgent(simulation, operation);
            operationAgents.add(operationAgent);
            simulation.execute(operationAgent);
        }
    }
}
