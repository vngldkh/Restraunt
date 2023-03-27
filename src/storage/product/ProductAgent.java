package storage.product;

import storage.product.Product;

public class ProductAgent {
    final Product product;
    double reserved_quantity;

    ProductAgent(Product product, double quantity) {
        this.product = product;
        synchronized (this.product) {
            if (product.prodItemQuantity < quantity) {
                reserved_quantity = product.prodItemQuantity;
                product.prodItemQuantity = 0;
            } else {
                reserved_quantity = quantity;
                product.prodItemQuantity -= quantity;
            }
        }
    }

    void cancelReservation() {
        synchronized (product) {
            product.prodItemQuantity += reserved_quantity;
        }
    }
}
