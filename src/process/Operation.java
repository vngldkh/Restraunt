package process;

import java.util.ArrayList;

public record Operation(int oper_type, int equip_type,
                        double oper_time, int oper_async_point,
                        ArrayList<OperProduct> oper_products) {
}
