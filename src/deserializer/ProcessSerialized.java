package deserializer;

import process.Operation;

import java.util.ArrayList;

public record ProcessSerialized(int proc_id, int ord_dish, String proc_started, String proc_ended,
                                ArrayList<OperationID> oper_id, boolean proc_active) {

}
