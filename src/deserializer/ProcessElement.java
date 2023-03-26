package deserializer;

import java.util.ArrayList;

public record ProcessElement(int proc_id, int ord_dish, String proc_started, String proc_ended,
                             boolean proc_active, ArrayList<OperationID> proc_operations) {

}
