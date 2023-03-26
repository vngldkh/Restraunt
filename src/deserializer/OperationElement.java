package deserializer;

public record OperationElement(int oper_id, int oper_proc, int oper_card,
                               String oper_started, String oper_ended,
                               int equip_id, int oper_cooker_id, boolean oper_active) {

}
