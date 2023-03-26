package storage;

import java.util.ArrayList;
import simulation.Simulation;

public class ProductTypeAgent {
    private final int product_type;
    private final ArrayList<ProductAgent> product_agents = new ArrayList<>();
    private final boolean reserved;
    private final Simulation simulation;

    ProductTypeAgent(Simulation simulation, int product_type, ArrayList<Product> products, double requested_quantity) {
        this.product_type = product_type;
        this.simulation = simulation;
        int i = 0;
        double reserved_quantity = 0;
        synchronized (simulation.getRestaurant().getStorage().storage) {
            while (i < products.size() && reserved_quantity < requested_quantity) {
                if (simulation.getCurrentTime().isBefore(products.get(i).prod_item_valid_until) &&
                        simulation.getCurrentTime().isAfter(products.get(i).prod_item_delivered)) {
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
    }

    public boolean Reserved() {
        return reserved;
    }

    public void CancelReservation() {
        synchronized (simulation.getRestaurant().getStorage().storage) {
            product_agents.forEach(ProductAgent::cancelReservation);
        }
    }

    public int GetProdType() {
        return product_type;
    }
}
