package Storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StorageAgent {
    // key - product_type, value - list of that type's products (sorted in ascending order of expiration time).
    HashMap<Integer, ArrayList<Product>> storage;

    public StorageAgent(Product[] products) {
        storage = new HashMap<>();
        for (Product product : products) {
            int type_id = product.prod_item_type;
            if (!storage.containsKey(type_id)) {
                storage.put(type_id, new ArrayList<>());
            }
            storage.get(type_id).add(product);
        }
    }

    public ProductTypeAgent ReserveProduct(int prod_type, int requested_quantity, Date current_time) {
        if (!storage.containsKey(prod_type)) {
            return new ProductTypeAgent(prod_type, new ArrayList<>(), requested_quantity, current_time);
        }
        return new ProductTypeAgent(prod_type, storage.get(prod_type), requested_quantity, current_time);
    }
}
