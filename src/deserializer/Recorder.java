package deserializer;

import java.util.ArrayList;

public class Recorder {
    ArrayList<ProcessElement> processes = new ArrayList<>();
    ArrayList<OperationElement> operations = new ArrayList<>();

    public void addProcess(ProcessElement process) {
        processes.add(process);
    }

    public void addOperation(OperationElement operation) {
        operations.add(operation);
    }

    public ArrayList<ProcessElement> getProcesses() {
        return processes;
    }

    public ArrayList<OperationElement> getOperations() {
        return operations;
    }
}
