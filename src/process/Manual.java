package process;

import java.util.HashMap;

public class Manual {
    HashMap<Integer, Operation> manual = new HashMap<>();

    public Manual(Operation[] operations) {
        for (var operation : operations) {
            manual.put(operation.oper_type(), operation);
        }
    }

    public Operation getOperation(int operType) {
        return manual.get(operType);
    }
}
