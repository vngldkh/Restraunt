package process;

public record Operation(int oper_type, int equip_type,
                        double oper_time, int oper_async_point,
                        OperProduct[] oper_products) {
}
