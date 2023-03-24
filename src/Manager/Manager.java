package Manager;

import Process.Cooker;
import Process.Equipment;
import Storage.StorageAgent;

import java.util.ArrayList;

public class Manager {
    Cooker[] cookers;
    Equipment[] equipments;
    ArrayList<OrderAgent> orders;
    ArrayList<DishCard> dish_cards;
    StorageAgent storage;
}
