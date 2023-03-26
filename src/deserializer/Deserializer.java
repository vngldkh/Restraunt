package deserializer;

import manager.DishCard;
import manager.MenuDish;
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
                } else {
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
                    prod_item_type = productJsonObject.get("prod_item_type").getAsInt();
                } else {
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
                    prod_item_name = productJsonObject.get("prod_item_name").getAsString();
                } else {
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
                    prod_item_company = productJsonObject.get("prod_item_company").getAsString();
                } else {
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
                    prod_item_unit = productJsonObject.get("prod_item_unit").getAsString();
                } else {
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
                    prod_item_quantity = productJsonObject.get("prod_item_quantity").getAsDouble();
                } else {
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
                    prod_item_cost = productJsonObject.get("prod_item_cost").getAsDouble();
                } else {
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
                    prod_item_delivered = productJsonObject.get("prod_item_delivered").getAsString();
                } else {
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
                    prod_item_valid_until = productJsonObject.get("prod_item_valid_until").getAsString();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product", "prod_item_valid_until");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
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
        File input = new File("./input/product_types.txt");
        ArrayList<ProductType> arrayOfProductTypes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfProducts = fileObject.get("product_types").getAsJsonArray();
            for (JsonElement productTypeElement : jsonArrayOfProducts) {
                JsonObject productTypeJsonObject = productTypeElement.getAsJsonObject();

                int prod_type_id = 0;
                String prod_type_name = null;
                boolean prod_is_food = false;

                if (productTypeJsonObject.has("prod_type_id")) {
                    prod_type_id = productTypeJsonObject.get("prod_type_id").getAsInt();
                } else {
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
                    prod_type_name = productTypeJsonObject.get("prod_type_name").getAsString();
                } else {
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
                    prod_is_food = productTypeJsonObject.get("prod_is_food").getAsBoolean();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "product_types", "prod_is_food");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
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
        File input = new File("./input/equipment.txt");
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
                boolean equip_active = false;

                if (equipmentJsonObject.has("equip_id")) {
                    equip_id = equipmentJsonObject.get("equip_id").getAsInt();
                } else {
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
                    equip_type = equipmentJsonObject.get("equip_type").getAsInt();
                } else {
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
                    equip_name = equipmentJsonObject.get("equip_name").getAsString();
                } else {
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
                    equip_active = equipmentJsonObject.get("equip_active").getAsBoolean();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment", "equip_active");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
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
        File input = new File("./input/equipment_types.txt");
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
                } else {
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
                    equip_type_name = equipmentTypeJsonObject.get("equip_type_name").getAsString();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "equipment_type", "equip_type_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
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

                    int ord_dish_id = 0;
                    int menu_dish = 0;
                    if (visitorDishJsonObject.has("ord_dish_id")) {
                        ord_dish_id = visitorDishJsonObject.get("ord_dish_id").getAsInt();
                    } else {
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
                        menu_dish = visitorDishJsonObject.get("menu_dish").getAsInt();
                    } else {
                        ErrorSerialized errors = new ErrorSerialized("input_error", "vis_ord_dishes", "menu_dish");
                        try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(errors);
                            out.write(jsonString);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
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
                boolean cook_active = false;

                if (cookerJsonObject.has("cook_id")) {
                    cook_id = cookerJsonObject.get("cook_id").getAsInt();
                } else {
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
                    cook_name = cookerJsonObject.get("cook_name").getAsString();
                } else {
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
                    cook_active = cookerJsonObject.get("cook_active").getAsBoolean();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "cookers", "cook_active");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
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
        File input = new File("./input/operation_types.txt");
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
                } else {
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
                    oper_type_name = operationJsonObject.get("oper_type_name").getAsString();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "operation_types", "oper_type_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
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

    public ArrayList<MenuDish> menuDishesDeserialize() {
        File input = new File("./input/menu_dishes.txt");
        ArrayList<MenuDish> arrayOfMenuDishes = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfMenuDishes = fileObject.get("menu_dishes").getAsJsonArray();
            for (JsonElement menuDishElement : jsonArrayOfMenuDishes) {
                JsonObject menuDishJsonObject = menuDishElement.getAsJsonObject();

                int menu_dish_id = 0;
                int menu_dish_card = 0;
                int menu_dish_price = 0;
                boolean menu_dish_active = false;

                if (menuDishJsonObject.has("menu_dish_id")) {
                    menu_dish_id = menuDishJsonObject.get("menu_dish_id").getAsInt();
                } else {
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
                    menu_dish_card = menuDishJsonObject.get("menu_dish_card").getAsInt();
                } else {
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
                    menu_dish_price = menuDishJsonObject.get("menu_dish_price").getAsInt();
                } else {
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
                    menu_dish_active = menuDishJsonObject.get("menu_dish_active").getAsBoolean();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "menu_dishes", "menu_dish_active");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                MenuDish menuDish = new MenuDish(menu_dish_id, menu_dish_card, menu_dish_price, menu_dish_active);
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

                int card_id = 0;
                String dish_name = null;
                String desription = null;
                double card_time = 0;

                ArrayList<Operation> operations = new ArrayList<>();

                if (dishCardsJsonObject.has("card_id")) {
                    card_id = dishCardsJsonObject.get("card_id").getAsInt();
                } else {
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
                    dish_name = dishCardsJsonObject.get("dish_name").getAsString();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "dish_cards", "dish_name");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (dishCardsJsonObject.has("desription")) {
                    desription = dishCardsJsonObject.get("desription").getAsString();
                } else {
                    ErrorSerialized errors = new ErrorSerialized("input_error", "dish_cards", "desription");
                    try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(errors);
                        out.write(jsonString);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                if (dishCardsJsonObject.has("card_time")) {
                    card_time = dishCardsJsonObject.get("card_time").getAsDouble();
                } else {
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

                    int oper_type = 0;
                    int equip_type = 0;
                    double oper_time = 0;
                    int oper_async_point = 0;
                    ArrayList<OperProduct> oper_products = new ArrayList<>();

                    if (operationsJsonObject.has("oper_type")) {
                        oper_type = operationsJsonObject.get("oper_type").getAsInt();
                    } else {
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
                        equip_type = operationsJsonObject.get("equip_type").getAsInt();
                    } else {
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
                        oper_time = operationsJsonObject.get("oper_time").getAsDouble();
                    } else {
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
                        oper_async_point = operationsJsonObject.get("oper_async_point").getAsInt();
                    } else {
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
                        int prod_type = 0;
                        double prod_quantity = 0;
                        if (operProductsJsonObject.has("prod_type")) {
                            prod_type = operProductsJsonObject.get("prod_type").getAsInt();
                        } else {
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
                            prod_quantity = operProductsJsonObject.get("prod_quantity").getAsDouble();
                        } else {
                            ErrorSerialized errors = new ErrorSerialized("input_error", "oper_products", "prod_quantity");
                            try (PrintWriter out = new PrintWriter(new FileWriter("error.txt"))) {
                                Gson gson = new Gson();
                                String jsonString = gson.toJson(errors);
                                out.write(jsonString);
                            } catch (Exception error) {
                                error.printStackTrace();
                            }
                        }
                        OperProduct operProduct = new OperProduct(prod_type, prod_quantity);
                        oper_products.add(operProduct);
                    }
                    Operation operation = new Operation(oper_type, equip_type, oper_time, oper_async_point, oper_products);
                    operations.add(operation);
                }

                DishCard dishCard = new DishCard(card_id, dish_name, desription, card_time, operations);
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
