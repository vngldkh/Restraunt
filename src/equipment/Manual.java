package equipment;

import order.process.operation.OperationType;

import java.util.ArrayList;
import java.util.HashMap;

public class Manual {
    HashMap<Integer, OperationType> manual = new HashMap<>();

    public Manual(ArrayList<OperationType> operations) {
        for (var operation : operations) {
            manual.put(operation.oper_type_id(), operation);
        }
    }

    public OperationType getOperation(int operType) {
        return manual.get(operType);
    }
}
