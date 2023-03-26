package storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import simulation.Simulation;

public class StorageAgent {
    // key - product_type, value - list of that type's products (sorted in ascending order of expiration time).
    final HashMap<Integer, ArrayList<Product>> storage;
    final Simulation simulation;

    public StorageAgent(Simulation simulation, Product[] products) {
        this.simulation = simulation;
        storage = new HashMap<>();
        for (Product product : products) {
            int type_id = product.prod_item_type;
            if (!storage.containsKey(type_id)) {
                storage.put(type_id, new ArrayList<>());
            }
            storage.get(type_id).add(product);
        }
    }

    public ProductTypeAgent ReserveProduct(int prod_type, double requested_quantity) {
        if (!storage.containsKey(prod_type)) {
            return new ProductTypeAgent(simulation, prod_type, new ArrayList<>(), requested_quantity);
        }
        return new ProductTypeAgent(simulation, prod_type, storage.get(prod_type), requested_quantity);
    }
}
