package serialization.entities;

import serialization.entities.OperationID;

import java.util.ArrayList;

public record ProcessElement(long proc_id, long ord_dish, String proc_started, String proc_ended,
                             boolean proc_active, ArrayList<OperationID> proc_operations) {

}
