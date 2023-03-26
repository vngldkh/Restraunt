package deserializer;

public record OperationSerialized(long oper_id, long oper_proc, long oper_card,
                                  String oper_started, String oper_ended,
                                  int equip_id, int oper_cooker_id, boolean oper_active) {
    public OperationID getId() {
        return new OperationID(oper_id);
    }
}
