package manager;

import process.Operation;
import process.OperationType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record DishCard(int card_id, String dish_name,
                       String description, double card_time,
                       ArrayList<Operation> operations) {
}
