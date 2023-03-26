package process;

import manager.DishCard;
import simulation.Simulation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProcessAgent implements Runnable {
    Simulation simulation;
    DishCard dishCard;
    ArrayList<OperationAgent> operationAgents;
    int menuDishId;
    int currentAsyncPoint = 0;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    boolean done = false;

    public ProcessAgent(Simulation simulation, DishCard dishCard, int menuDishId) {
        this.simulation = simulation;
        this.dishCard = dishCard;
        this.menuDishId = menuDishId;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public void run() {
        timeStart = simulation.getCurrentTime();

        Manual manual = simulation.getRestaurant().getManager().provideManual();
        ArrayList<Operation> operationTypes = dishCard.operations();
        operationAgents = new ArrayList<>();
        for (var operationType : operationTypes) {
            Operation operation = manual.getOperation(operationType.oper_type());
            OperationAgent operationAgent = new OperationAgent(simulation, operation, dishCard.card_id());
            operationAgents.add(operationAgent);
        }

        while (!operationAgents.isEmpty()) {
            for (var operationAgent : operationAgents) {
                if (operationAgent.getAsyncPoint() == currentAsyncPoint) {
                    simulation.execute(operationAgent);
                }
            }

            synchronized (simulation.monitor) {
                try {
                    wait(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            int started = 0;
            int completed = 0;
            for (var operationAgent : operationAgents) {
                if (operationAgent.getAsyncPoint() == currentAsyncPoint) {
                    ++started;
                }
                if (operationAgent.isDone()) {
                    ++completed;
                    operationAgents.remove(operationAgent);
                }
            }
            if (started == completed) {
                ++currentAsyncPoint;
            }
        }

        timeFinish = simulation.getCurrentTime();
        done = true;
        log();
    }

    void log() {
        // TODO
    }
}
