package Storage;

public class ProductAgent {
    final Product product;
    double reserved_quantity;

    ProductAgent(Product product, double quantity) {
        this.product = product;
        synchronized (this.product) {
            if (product.prod_item_quantity < quantity) {
                reserved_quantity = product.prod_item_quantity;
                product.prod_item_quantity = 0;
            } else {
                reserved_quantity = quantity;
                product.prod_item_quantity -= quantity;
            }
        }
    }

    void cancelReservation() {
        synchronized (product) {
            product.prod_item_quantity += reserved_quantity;
        }
    }
}
