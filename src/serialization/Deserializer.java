package serialization;

import cooker.Cooker;
import dish.card.DishCard;
import dish.menu.MenuDish;
import equipment.Equipment;
import equipment.EquipmentType;
import order.process.operation.OperProduct;
import order.process.operation.Operation;
import order.process.operation.OperationType;
import serialization.entities.ErrorSerialized;
import storage.product.Product;
import com.google.gson.*;
import storage.product.ProductType;
import visitor.VisOrdDish;
import visitor.Visitor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Deserializer {
    boolean flag = false;

    public boolean hadMissed() {
        return flag;
    }

    public ArrayList<Product> productDeserialize() {
        File input = new File("./input/products.txt");
        ArrayList<Product> arrayOfProducts = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("products").getAsJsonArray();
            for (JsonElement productElement : jsonArrayOfProducts) {
                JsonObject productJsonObject = productElement.getAsJsonObject();

                int prodItemId = 0;
                int prodItemType = 0;
                String prodItemName = null;
                String prodItemCompany = null;
                String prodItemUnit = null;
                double prodItemQuantity = 0;
                double prodItemCost = 0;
                String prodItemDelivered = null;
                String prodItemValidUntil = null;

                if (productJsonObject.has("prod_item_id")) {
                    prodItemId = productJsonObject.get("prod_item_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_type")) {
                    prodItemType = productJsonObject.get("prod_item_type").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_type");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_name")) {
                    prodItemName = productJsonObject.get("prod_item_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_company")) {
                    prodItemCompany = productJsonObject.get("prod_item_company").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_company");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_unit")) {
                    prodItemUnit = productJsonObject.get("prod_item_unit").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_unit");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_quantity")) {
                    prodItemQuantity = productJsonObject.get("prod_item_quantity").getAsDouble();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_quantity");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_cost")) {
                    prodItemCost = productJsonObject.get("prod_item_cost").getAsDouble();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_cost");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_delivered")) {
                    prodItemDelivered = productJsonObject.get("prod_item_delivered").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_delivered");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productJsonObject.has("prod_item_valid_until")) {
                    prodItemValidUntil = productJsonObject.get("prod_item_valid_until").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_valid_until");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                LocalDateTime date1 = LocalDateTime.parse(prodItemDelivered, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime date2 = LocalDateTime.parse(prodItemValidUntil, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                Product product = new Product(prodItemId,
                        prodItemType,
                        prodItemName,
                        prodItemCompany,
                        prodItemUnit,
                        prodItemQuantity,
                        prodItemCost,
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
        File input = new File("./input/product_types.txt");
        ArrayList<ProductType> arrayOfProductTypes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("product_types").getAsJsonArray();
            for (JsonElement productTypeElement : jsonArrayOfProducts) {
                JsonObject productTypeJsonObject = productTypeElement.getAsJsonObject();

                int prodTypeId = 0;
                String prodTypeName = null;
                boolean prodIsFood = false;

                if (productTypeJsonObject.has("prod_type_id")) {
                    prodTypeId = productTypeJsonObject.get("prod_type_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product_types", "prod_type_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productTypeJsonObject.has("prod_type_name")) {
                    prodTypeName = productTypeJsonObject.get("prod_type_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product_types", "prod_type_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (productTypeJsonObject.has("prod_is_food")) {
                    prodIsFood = productTypeJsonObject.get("prod_is_food").getAsBoolean();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product_types", "prod_is_food");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                ProductType productType = new ProductType(prodTypeId,
                        prodTypeName,
                        prodIsFood);
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
        File input = new File("./input/equipment.txt");
        ArrayList<Equipment> arrayOfEquipment = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("equipment").getAsJsonArray();
            for (JsonElement equipmentElement : jsonArrayOfProducts) {
                JsonObject equipmentJsonObject = equipmentElement.getAsJsonObject();

                int equipId = 0;
                int equipType = 0;
                String equipName = null;
                boolean equipActive = false;

                if (equipmentJsonObject.has("equip_id")) {
                    equipId = equipmentJsonObject.get("equip_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment", "equip_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (equipmentJsonObject.has("equip_type")) {
                    equipType = equipmentJsonObject.get("equip_type").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment", "equip_type");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (equipmentJsonObject.has("equip_name")) {
                    equipName = equipmentJsonObject.get("equip_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment", "equip_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (equipmentJsonObject.has("equip_active")) {
                    equipActive = equipmentJsonObject.get("equip_active").getAsBoolean();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment", "equip_active");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                Equipment equipment = new Equipment(equipId,
                        equipType,
                        equipName,
                        equipActive);
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
        File input = new File("./input/equipment_types.txt");
        ArrayList<EquipmentType> arrayOfEquipmentTypes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("equipment_type").getAsJsonArray();
            for (JsonElement equipmentTypeElement : jsonArrayOfProducts) {
                JsonObject equipmentTypeJsonObject = equipmentTypeElement.getAsJsonObject();

                int equipTypeId = 0;
                String equipTypeName = null;

                if (equipmentTypeJsonObject.has("equip_type_id")) {
                    equipTypeId = equipmentTypeJsonObject.get("equip_type_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment_type", "equip_type_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (equipmentTypeJsonObject.has("equip_type_name")) {
                    equipTypeName = equipmentTypeJsonObject.get("equip_type_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment_type", "equip_type_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                EquipmentType equipmentTypes = new EquipmentType(equipTypeId,
                        equipTypeName);
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
        File input = new File("./input/visitor_orders.txt");
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
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "visitors_orders", "vis_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (visitorOrderJsonObject.has("vis_ord_started")) {
                    started = visitorOrderJsonObject.get("vis_ord_started").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "visitors_orders", "vis_ord_started");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (visitorOrderJsonObject.has("vis_ord_ended")) {
                    ended = visitorOrderJsonObject.get("vis_ord_ended").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "visitors_orders", "vis_ord_ended");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (visitorOrderJsonObject.has("vis_ord_total")) {
                    total = visitorOrderJsonObject.get("vis_ord_total").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "visitors_orders", "vis_ord_total");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                JsonArray jsonArrayOfVisitorDishes = visitorOrderJsonObject.get("vis_ord_dishes").getAsJsonArray();
                for (JsonElement visitorDishElement : jsonArrayOfVisitorDishes) {
                    JsonObject visitorDishJsonObject = visitorDishElement.getAsJsonObject();

                    int ordDishId = 0;
                    int menuDish = 0;
                    if (visitorDishJsonObject.has("ord_dish_id")) {
                        ordDishId = visitorDishJsonObject.get("ord_dish_id").getAsInt();
                    } else {
                        flag = true;
                        ErrorSerialized errors = new ErrorSerialized("input_error", "vis_ord_dishes", "ord_dish_id");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    if (visitorDishJsonObject.has("menu_dish")) {
                        menuDish = visitorDishJsonObject.get("menu_dish").getAsInt();
                    } else {
                        flag = true;
                        ErrorSerialized errors = new ErrorSerialized("input_error", "vis_ord_dishes", "menu_dish");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }

                    VisOrdDish visOrdDish = new VisOrdDish(ordDishId, menuDish);
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

                int cookId = 0;
                String cookName = null;
                boolean cookActive = false;

                if (cookerJsonObject.has("cook_id")) {
                    cookId = cookerJsonObject.get("cook_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "cookers", "cook_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (cookerJsonObject.has("cook_name")) {
                    cookName = cookerJsonObject.get("cook_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "cookers", "cook_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (cookerJsonObject.has("cook_active")) {
                    cookActive = cookerJsonObject.get("cook_active").getAsBoolean();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "cookers", "cook_active");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                Cooker cooker = new Cooker(cookId, cookName, cookActive);
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
        File input = new File("./input/operation_types.txt");
        ArrayList<OperationType> arrayOfOperations = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfOperations = fileObject.get("operation_types").getAsJsonArray();
            for (JsonElement operationElement : jsonArrayOfOperations) {
                JsonObject operationJsonObject = operationElement.getAsJsonObject();

                int operTypeId = 0;
                String operTypeName = null;

                if (operationJsonObject.has("oper_type_id")) {
                    operTypeId = operationJsonObject.get("oper_type_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "operation_types", "oper_type_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (operationJsonObject.has("oper_type_name")) {
                    operTypeName = operationJsonObject.get("oper_type_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "operation_types", "oper_type_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                OperationType operation = new OperationType(operTypeId, operTypeName);
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

    public ArrayList<MenuDish> menuDishesDeserialize() {
        File input = new File("./input/menu_dishes.txt");
        ArrayList<MenuDish> arrayOfMenuDishes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfMenuDishes = fileObject.get("menu_dishes").getAsJsonArray();
            for (JsonElement menuDishElement : jsonArrayOfMenuDishes) {
                JsonObject menuDishJsonObject = menuDishElement.getAsJsonObject();

                int menuDishId = 0;
                int menuDishCard = 0;
                int menuDishPrice = 0;
                boolean menuDishActive = false;

                if (menuDishJsonObject.has("menu_dish_id")) {
                    menuDishId = menuDishJsonObject.get("menu_dish_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "menu_dishes", "menu_dish_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (menuDishJsonObject.has("menu_dish_card")) {
                    menuDishCard = menuDishJsonObject.get("menu_dish_card").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "menu_dishes", "menu_dish_card");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (menuDishJsonObject.has("menu_dish_price")) {
                    menuDishPrice = menuDishJsonObject.get("menu_dish_price").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "menu_dishes", "menu_dish_price");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (menuDishJsonObject.has("menu_dish_active")) {
                    menuDishActive = menuDishJsonObject.get("menu_dish_active").getAsBoolean();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "menu_dishes", "menu_dish_active");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                MenuDish menuDish = new MenuDish(menuDishId, menuDishCard, menuDishPrice, menuDishActive);
                arrayOfMenuDishes.add(menuDish);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfMenuDishes;
    }

    public ArrayList<DishCard> dishCardsDeserialize() {
        File input = new File("./input/dish_cards.txt");
        ArrayList<DishCard> arrayOfDishCards = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfMenuDishes = fileObject.get("dish_cards").getAsJsonArray();
            for (JsonElement dishCardsElement : jsonArrayOfMenuDishes) {
                JsonObject dishCardsJsonObject = dishCardsElement.getAsJsonObject();

                int cardId = 0;
                String dishName = null;
                String description = null;
                double cardTime = 0;

                ArrayList<Operation> operations = new ArrayList<>();

                if (dishCardsJsonObject.has("card_id")) {
                    cardId = dishCardsJsonObject.get("card_id").getAsInt();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "dish_cards", "card_id");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (dishCardsJsonObject.has("dish_name")) {
                    dishName = dishCardsJsonObject.get("dish_name").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "dish_cards", "dish_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (dishCardsJsonObject.has("card_descr")) {
                    description = dishCardsJsonObject.get("card_descr").getAsString();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "dish_cards", "card_descr");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (dishCardsJsonObject.has("card_time")) {
                    cardTime = dishCardsJsonObject.get("card_time").getAsDouble();
                } else {
                    flag = true;
                    ErrorSerialized errors = new ErrorSerialized("input_error", "dish_cards", "card_time");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                JsonArray jsonArrayOfOperations = dishCardsJsonObject.get("operations").getAsJsonArray();
                for (JsonElement operationsElement : jsonArrayOfOperations) {
                    JsonObject operationsJsonObject = operationsElement.getAsJsonObject();

                    int operType = 0;
                    int equipType = 0;
                    double operTime = 0;
                    int operAsyncPoint = 0;
                    ArrayList<OperProduct> operProducts = new ArrayList<>();

                    if (operationsJsonObject.has("oper_type")) {
                        operType = operationsJsonObject.get("oper_type").getAsInt();
                    } else {
                        flag = true;
                        ErrorSerialized errors = new ErrorSerialized("input_error", "operations", "oper_type");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    if (operationsJsonObject.has("equip_type")) {
                        equipType = operationsJsonObject.get("equip_type").getAsInt();
                    } else {
                        flag = true;
                        ErrorSerialized errors = new ErrorSerialized("input_error", "operations", "equip_type");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    if (operationsJsonObject.has("oper_time")) {
                        operTime = operationsJsonObject.get("oper_time").getAsDouble();
                    } else {
                        flag = true;
                        ErrorSerialized errors = new ErrorSerialized("input_error", "operations", "oper_time");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    if (operationsJsonObject.has("oper_async_point")) {
                        operAsyncPoint = operationsJsonObject.get("oper_async_point").getAsInt();
                    } else {
                        flag = true;
                        ErrorSerialized errors = new ErrorSerialized("input_error", "operations", "oper_async_point");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }

                    JsonArray jsonArrayOfOperProducts = operationsJsonObject.get("oper_products").getAsJsonArray();
                    for (JsonElement operProductsElement : jsonArrayOfOperProducts) {
                        JsonObject operProductsJsonObject = operProductsElement.getAsJsonObject();
                        int prodType = 0;
                        double prodQuantity = 0;
                        if (operProductsJsonObject.has("prod_type")) {
                            prodType = operProductsJsonObject.get("prod_type").getAsInt();
                        } else {
                            flag = true;
                            ErrorSerialized errors = new ErrorSerialized("input_error", "oper_products", "prod_type");
                            try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                                Gson gson = new Gson();
                                String jsonString = gson.toJson(errors);
                                out.write(jsonString);
                            } catch (Exception error) {
                                error.printStackTrace();
                            }
                        }
                        if (operProductsJsonObject.has("prod_quantity")) {
                            prodQuantity = operProductsJsonObject.get("prod_quantity").getAsDouble();
                        } else {
                            flag = true;
                            ErrorSerialized errors = new ErrorSerialized("input_error", "oper_products", "prod_quantity");
                            try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                                Gson gson = new Gson();
                                String jsonString = gson.toJson(errors);
                                out.write(jsonString);
                            } catch (Exception error) {
                                error.printStackTrace();
                            }
                        }
                        OperProduct operProduct = new OperProduct(prodType, prodQuantity);
                        operProducts.add(operProduct);
                    }
                    Operation operation = new Operation(operType, equipType, operTime, operAsyncPoint, operProducts);
                    operations.add(operation);
                }

                DishCard dishCard = new DishCard(cardId, dishName, description, cardTime, operations);
                arrayOfDishCards.add(dishCard);
            }
        } catch (FileNotFoundException error) {
            System.err.println("Error: input file not found!");
            error.printStackTrace();
        } catch (Exception error) {
            System.err.println("Error: specific problem!");
            error.printStackTrace();
        }
        return arrayOfDishCards;
    }
}
