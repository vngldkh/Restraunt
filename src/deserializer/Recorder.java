package deserializer;

import java.util.ArrayList;

public class Recorder {
    ArrayList<ProcessSerialized> processes = new ArrayList<>();
    ArrayList<OperationSerialized> operations = new ArrayList<>();

    public void addProcess(ProcessSerialized process) {
        processes.add(process);
    }

    public void addOperation(OperationSerialized operation) {
        operations.add(operation);
    }

    public ArrayList<ProcessSerialized> getProcesses() {
        return processes;
    }

    public ArrayList<OperationSerialized> getOperations() {
        return operations;
    }
}
