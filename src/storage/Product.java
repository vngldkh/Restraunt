package storage;

import java.time.LocalDateTime;

public class Product {
    int prod_item_id;
    int prod_item_type;
    String prod_item_name;
    String prod_item_company;
    String prod_item_unit;
    double prod_item_quantity;
    double prod_item_cost;
    LocalDateTime prod_item_delivered;
    LocalDateTime prod_item_valid_until;

    public Product(int prod_item_id,
                          int prod_item_type,
                          String prod_item_name,
                          String prod_item_company,
                          String prod_item_unit,
                          double prod_item_quantity,
                          double prod_item_cost,
                          LocalDateTime prod_item_delivered,
                          LocalDateTime prod_item_valid_until) {

        this.prod_item_id = prod_item_id;
        this.prod_item_type = prod_item_type;
        this.prod_item_name = prod_item_name;
        this.prod_item_company = prod_item_company;
        this.prod_item_unit = prod_item_unit;
        this.prod_item_quantity = prod_item_quantity;
        this.prod_item_cost = prod_item_cost;
        this.prod_item_delivered = prod_item_delivered;
        this.prod_item_valid_until = prod_item_valid_until;
    }


}
