package Storage;

import java.util.ArrayList;
import java.util.Date;

public class ProductTypeAgent {
    private int product_type;
    private final ArrayList<ProductAgent> product_agents = new ArrayList<>();
    private final boolean reserved;

    ProductTypeAgent(int product_type, ArrayList<Product> products, double requested_quantity, Date date) {
        this.product_type = product_type;
        int i = 0;
        double reserved_quantity = 0;
        while (i < products.size() && reserved_quantity < requested_quantity) {
            if (date.before(products.get(i).prod_item_valid_until)) {
                product_agents.add(new ProductAgent(products.get(i), requested_quantity - reserved_quantity));
                reserved_quantity += product_agents.get(i).reserved_quantity;
            }
            ++i;
        }
        reserved = reserved_quantity == requested_quantity;
        if (!reserved) {
            CancelReservation();
        }
    }

    public boolean Reserved() {
        return reserved;
    }

    public void CancelReservation() {
        product_agents.forEach(ProductAgent::cancelReservation);
    }

    public int GetProdType() {
        return product_type;
    }
}
