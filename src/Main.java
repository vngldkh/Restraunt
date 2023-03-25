import deserializer.Deserializer;
import process.Equipment;
import process.EquipmentType;
import storage.Product;
import storage.ProductType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        Deserializer deserializerProducts = new Deserializer();
        products = deserializerProducts.productDeserialize();

        ArrayList<ProductType> productTypes = new ArrayList<>();
        Deserializer deserializerProductTypes = new Deserializer();
        productTypes = deserializerProductTypes.productTypeDeserialize();

        ArrayList<Equipment> equipment = new ArrayList<>();
        Deserializer deserializerEquipment = new Deserializer();
        equipment = deserializerEquipment.equipmentDeserialize();

        ArrayList<EquipmentType> equipmentTypes = new ArrayList<>();
        Deserializer deserializerEquipmentTypes = new Deserializer();
        equipmentTypes = deserializerEquipmentTypes.equipmentTypesDeserialize();

        int check = 0;
    // TODO: replace snake_case in the classes with camelCase

    }
}