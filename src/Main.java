import serialization.Deserializer;
import dish.card.DishCard;
import dish.menu.MenuDish;
import cooker.Cooker;
import equipment.Equipment;
import equipment.EquipmentType;
import order.process.operation.OperationType;
import simulation.Simulation;
import storage.product.Product;
import storage.product.ProductType;
import visitor.Visitor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Deserializer deserializer = new Deserializer();
        ArrayList<Product> products = deserializer.productDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<ProductType> productTypes = deserializer.productTypeDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<Equipment> equipment = deserializer.equipmentDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<EquipmentType> equipmentTypes = deserializer.equipmentTypesDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<Visitor> visitors = deserializer.visitorOrdersDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<Cooker> cookers = deserializer.cookersDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<OperationType> operations = deserializer.operationsDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<MenuDish> menuDishes = deserializer.menuDishesDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }

        ArrayList<DishCard> dishCards = deserializer.dishCardsDeserialize();
        if (deserializer.hadMissed()) {
            System.err.println("Error.txt: Пропущено обязательное поле.");
            return;
        }


        Simulation simulation = new Simulation(LocalDateTime.parse( "2023-02-28T10:12:37", DateTimeFormatter.ISO_LOCAL_DATE_TIME), products, cookers, equipment, operations, dishCards, menuDishes, visitors);
        simulation.run();
    }
}