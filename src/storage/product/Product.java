package storage.product;

import java.time.LocalDateTime;

public class Product {
    int prodItemId;
    public int prodItemType;
    String prodItemName;
    String prodItemCompany;
    String prodItemUnit;
    double prodItemQuantity;
    double prodItemCost;
    LocalDateTime prodItemDelivered;
    LocalDateTime prodItemValidUntil;

    public Product(int prodItemId, int prodItemType, String prodItemName, String prodItemCompany,
                   String prodItemUnit, double prodItemQuantity, double prodItemCost,
                   LocalDateTime prodItemDelivered, LocalDateTime prodItemValidUntil) {
        this.prodItemId = prodItemId;
        this.prodItemType = prodItemType;
        this.prodItemName = prodItemName;
        this.prodItemCompany = prodItemCompany;
        this.prodItemUnit = prodItemUnit;
        this.prodItemQuantity = prodItemQuantity;
        this.prodItemCost = prodItemCost;
        this.prodItemDelivered = prodItemDelivered;
        this.prodItemValidUntil = prodItemValidUntil;
    }


}
