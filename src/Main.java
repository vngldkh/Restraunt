import deserializer.Deserializer;
import deserializer.OperationID;
import deserializer.ProccessSerialized;
import deserializer.ProcessElement;
import manager.DishCard;
import manager.MenuDish;
import process.Cooker;
import process.Equipment;
import process.EquipmentType;
import process.OperationType;
import simulation.Simulation;
import storage.Product;
import storage.ProductType;
import visitor.Visitor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.*;

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


        Simulation simulation = new Simulation(LocalDateTime.parse( "2023-02-28T10:12:37", DateTimeFormatter.ISO_LOCAL_DATE_TIME), products, cookers, equipment, operations, dishCards, menuDishes, visitors);
        simulation.run();
    }
}