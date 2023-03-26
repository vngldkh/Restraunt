package deserializer;

import process.*;
import storage.Product;
import com.google.gson.*;
import storage.ProductType;
import visitor.VisOrdDish;
import visitor.Visitor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Deserializer {
    public ArrayList<Product> productDeserialize() {
        File input = new File("./input/products.txt");
        ArrayList<Product> arrayOfProducts = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("products").getAsJsonArray();
            for (JsonElement productElement : jsonArrayOfProducts) {
                JsonObject productJsonObject = productElement.getAsJsonObject();

                int prod_item_id = 0;
                int prod_item_type = 0;
                String prod_item_name = null;
                String prod_item_company = null;
                String prod_item_unit = null;
                double prod_item_quantity = 0;
                double prod_item_cost = 0;
                String prod_item_delivered = null;
                String prod_item_valid_until = null;

                if (productJsonObject.has("prod_item_id")) {
                    prod_item_id = productJsonObject.get("prod_item_id").getAsInt();
                }
                if (productJsonObject.has("prod_item_type")) {
                    prod_item_type = productJsonObject.get("prod_item_type").getAsInt();
                }
                if (productJsonObject.has("prod_item_name")) {
                    prod_item_name = productJsonObject.get("prod_item_name").getAsString();
                }
                if (productJsonObject.has("prod_item_company")) {
                    prod_item_company = productJsonObject.get("prod_item_company").getAsString();
                }
                if (productJsonObject.has("prod_item_unit")) {
                    prod_item_unit = productJsonObject.get("prod_item_unit").getAsString();
                }
                if (productJsonObject.has("prod_item_quantity")) {
                    prod_item_quantity = productJsonObject.get("prod_item_quantity").getAsDouble();
                }
                if (productJsonObject.has("prod_item_cost")) {
                    prod_item_cost = productJsonObject.get("prod_item_cost").getAsDouble();
                }
                if (productJsonObject.has("prod_item_delivered")) {
                    prod_item_delivered = productJsonObject.get("prod_item_delivered").getAsString();
                }
                if (productJsonObject.has("prod_item_valid_until")) {
                    prod_item_valid_until = productJsonObject.get("prod_item_valid_until").getAsString();
                }

                LocalDateTime date1 = LocalDateTime.parse(prod_item_delivered, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime date2 = LocalDateTime.parse(prod_item_valid_until, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                Product product = new Product(prod_item_id,
                        prod_item_type,
                        prod_item_name,
                        prod_item_company,
                        prod_item_unit,
                        prod_item_quantity,
                        prod_item_cost,
                        date1,
                        date2);
                arrayOfProducts.add(product);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfProducts;
    }


    public ArrayList<ProductType> productTypeDeserialize() {
        File input = new File("./input/productTypes.txt");
        ArrayList<ProductType> arrayOfProductTypes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("product_types").getAsJsonArray();
            for (JsonElement productTypeElement : jsonArrayOfProducts) {
                JsonObject productTypeJsonObject = productTypeElement.getAsJsonObject();

                int prod_type_id = 0;
                String prod_type_name = null;
                Boolean prod_is_food = false;

                if (productTypeJsonObject.has("prod_type_id")) {
                    prod_type_id = productTypeJsonObject.get("prod_type_id").getAsInt();
                }
                if (productTypeJsonObject.has("prod_type_name")) {
                    prod_type_name = productTypeJsonObject.get("prod_type_name").getAsString();
                }
                if (productTypeJsonObject.has("prod_is_food")) {
                    prod_is_food = productTypeJsonObject.get("prod_is_food").getAsBoolean();
                }

                ProductType productType = new ProductType(prod_type_id,
                        prod_type_name,
                        prod_is_food);
                arrayOfProductTypes.add(productType);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfProductTypes;
    }

    public ArrayList<Equipment> equipmentDeserialize() {
        File input = new File("./input/equipments.txt");
        ArrayList<Equipment> arrayOfEquipment = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("equipment").getAsJsonArray();
            for (JsonElement equipmentElement : jsonArrayOfProducts) {
                JsonObject equipmentJsonObject = equipmentElement.getAsJsonObject();

                int equip_id = 0;
                int equip_type = 0;
                String equip_name = null;
                Boolean equip_active = false;

                if (equipmentJsonObject.has("equip_id")) {
                    equip_id = equipmentJsonObject.get("equip_id").getAsInt();
                }
                if (equipmentJsonObject.has("equip_type")) {
                    equip_type = equipmentJsonObject.get("equip_type").getAsInt();
                }
                if (equipmentJsonObject.has("equip_name")) {
                    equip_name = equipmentJsonObject.get("equip_name").getAsString();
                }
                if (equipmentJsonObject.has("equip_active")) {
                    equip_active = equipmentJsonObject.get("equip_active").getAsBoolean();
                }

                Equipment equipment = new Equipment(equip_id,
                        equip_type,
                        equip_name,
                        equip_active);
                arrayOfEquipment.add(equipment);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfEquipment;
    }

    public ArrayList<EquipmentType> equipmentTypesDeserialize() {
        File input = new File("./input/equipmentTypes.txt");
        ArrayList<EquipmentType> arrayOfEquipmentTypes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("equipment_type").getAsJsonArray();
            for (JsonElement equipmentTypeElement : jsonArrayOfProducts) {
                JsonObject equipmentTypeJsonObject = equipmentTypeElement.getAsJsonObject();

                int equip_type_id = 0;
                String equip_type_name = null;

                if (equipmentTypeJsonObject.has("equip_type_id")) {
                    equip_type_id = equipmentTypeJsonObject.get("equip_type_id").getAsInt();
                }
                if (equipmentTypeJsonObject.has("equip_type_name")) {
                    equip_type_name = equipmentTypeJsonObject.get("equip_type_name").getAsString();
                }

                EquipmentType equipmentTypes = new EquipmentType(equip_type_id,
                        equip_type_name);
                arrayOfEquipmentTypes.add(equipmentTypes);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfEquipmentTypes;
    }

    public ArrayList<Visitor> visitorOrdersDeserialize() {
        File input = new File("./input/visitorOrders.txt");
        ArrayList<Visitor> arrayOfVisitorOrders = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfVisitors = fileObject.get("visitors_orders").getAsJsonArray();
            for (JsonElement visitorOrderElement : jsonArrayOfVisitors) {
                JsonObject visitorOrderJsonObject = visitorOrderElement.getAsJsonObject();

                String name = null;
                String started = null;
                String ended = null;
                int total = 0;
                ArrayList<VisOrdDish> dishes = new ArrayList<>();

                if (visitorOrderJsonObject.has("vis_name")) {
                    name = visitorOrderJsonObject.get("vis_name").getAsString();
                }
                if (visitorOrderJsonObject.has("vis_ord_started")) {
                    started = visitorOrderJsonObject.get("vis_ord_started").getAsString();
                }
                if (visitorOrderJsonObject.has("vis_ord_ended")) {
                    ended = visitorOrderJsonObject.get("vis_ord_ended").getAsString();
                }
                if (visitorOrderJsonObject.has("vis_ord_total")) {
                    total = visitorOrderJsonObject.get("vis_ord_total").getAsInt();
                }

                JsonArray jsonArrayOfVisitorDishes = visitorOrderJsonObject.get("vis_ord_dishes").getAsJsonArray();
                for (JsonElement visitorDishElement : jsonArrayOfVisitorDishes) {
                    JsonObject visitorDishJsonObject = visitorDishElement.getAsJsonObject();

                    int ord_dish_id = 0;
                    int menu_dish = 0;
                    if (visitorDishJsonObject.has("ord_dish_id")) {
                        ord_dish_id = visitorDishJsonObject.get("ord_dish_id").getAsInt();
                    }
                    if (visitorDishJsonObject.has("menu_dish")) {
                        menu_dish = visitorDishJsonObject.get("menu_dish").getAsInt();
                    }

                    VisOrdDish visOrdDish = new VisOrdDish(ord_dish_id, menu_dish);
                    dishes.add(visOrdDish);
                }

                LocalDateTime date1 = LocalDateTime.parse(started, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime date2 = LocalDateTime.parse(ended, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                Visitor visitor = new Visitor(name, date1, date2, total, dishes);
                arrayOfVisitorOrders.add(visitor);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfVisitorOrders;
    }

    public ArrayList<Cooker> cookersDeserialize() {
        File input = new File("./input/cookers.txt");
        ArrayList<Cooker> arrayOfCookers = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfCookers = fileObject.get("cookers").getAsJsonArray();
            for (JsonElement cookerElement : jsonArrayOfCookers) {
                JsonObject cookerJsonObject = cookerElement.getAsJsonObject();

                int cook_id = 0;
                String cook_name = null;
                Boolean cook_active = false;

                if (cookerJsonObject.has("cook_id")) {
                    cook_id = cookerJsonObject.get("cook_id").getAsInt();
                }
                if (cookerJsonObject.has("cook_name")) {
                    cook_name = cookerJsonObject.get("cook_name").getAsString();
                }
                if (cookerJsonObject.has("cook_active")) {
                    cook_active = cookerJsonObject.get("cook_active").getAsBoolean();
                }
                Cooker cooker = new Cooker(cook_id, cook_name, cook_active);
                arrayOfCookers.add(cooker);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfCookers;
    }

    public ArrayList<OperationType> operationsDeserialize() {
        File input = new File("./input/operations.txt");
        ArrayList<OperationType> arrayOfOperations = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfOperations = fileObject.get("operation_types").getAsJsonArray();
            for (JsonElement operationElement : jsonArrayOfOperations) {
                JsonObject operationJsonObject = operationElement.getAsJsonObject();

                int oper_type_id = 0;
                String oper_type_name = null;

                if (operationJsonObject.has("oper_type_id")) {
                    oper_type_id = operationJsonObject.get("oper_type_id").getAsInt();
                }
                if (operationJsonObject.has("oper_type_name")) {
                    oper_type_name = operationJsonObject.get("oper_type_name").getAsString();
                }

                OperationType operation = new OperationType(oper_type_id, oper_type_name);
                arrayOfOperations.add(operation);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfOperations;
    }
}
