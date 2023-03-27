package storage.product;

import java.time.LocalDateTime;

public class ProductType {
    int prod_type_id;
    String prod_type_name;
    Boolean prod_is_food;

    public ProductType(int prod_type_id,
                   String prod_type_name,
                   Boolean prod_is_food) {

        this.prod_type_id = prod_type_id;
        this.prod_type_name = prod_type_name;
        this.prod_is_food = prod_is_food;
    }
}
