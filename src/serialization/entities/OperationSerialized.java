package serialization.entities;

import serialization.entities.OperationElement;

import java.util.ArrayList;

public record OperationSerialized(ArrayList<OperationElement> operation_log) {

}
