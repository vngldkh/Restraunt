import deserializer.Deserializer;
import manager.DishCard;
import manager.MenuDish;
import process.Cooker;
import process.Equipment;
import process.EquipmentType;
import process.OperationType;
import storage.Product;
import storage.ProductType;
import visitor.Visitor;

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

        ArrayList<Visitor> visitors = new ArrayList<>();
        Deserializer deserializerVisitors = new Deserializer();
        visitors = deserializerVisitors.visitorOrdersDeserialize();

        ArrayList<Cooker> cookers = new ArrayList<>();
        Deserializer deserializerCookers = new Deserializer();
        cookers = deserializerCookers.cookersDeserialize();

        ArrayList<OperationType> operations = new ArrayList<>();
        Deserializer deserializerOperations = new Deserializer();
        operations = deserializerOperations.operationsDeserialize();

        ArrayList<MenuDish> menuDishes = new ArrayList<>();
        Deserializer deserializerMenuDishes = new Deserializer();
        menuDishes = deserializerMenuDishes.menuDishesDeserialize();

        ArrayList<DishCard> dishCards = new ArrayList<>();
        Deserializer deserializerDishCards = new Deserializer();
        dishCards = deserializerDishCards.dishCardsDeserialize();

        int check = 0;
    // TODO: replace snake_case in the classes with camelCase

    }
}