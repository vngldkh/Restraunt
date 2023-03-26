package deserializer;

public record OperationElement(long oper_id, long oper_proc, long oper_card,
                               String oper_started, String oper_ended,
                               long equip_id, long oper_cooker_id, boolean oper_active) {

    public OperationID getId() {
        return new OperationID(oper_id);
    }
}
