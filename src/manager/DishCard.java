package manager;

import process.OperationType;

import java.util.Date;
import java.util.ArrayList;

public record DishCard(int card_id, String dish_name,
                       Date card_time, int equip_type,
                       ArrayList<OperationType> operations) {
}
