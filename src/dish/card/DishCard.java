package dish.card;

import order.process.operation.Operation;

import java.util.ArrayList;

public record DishCard(int cardId, String dishName,
                       String description, double cardTime,
                       ArrayList<Operation> operations) {
}
