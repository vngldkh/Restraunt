package manager;

import java.util.HashMap;

public class Handbook {
    HashMap<Integer, DishCard> handbook = new HashMap<>();

    Handbook(DishCard[] dishCards) {
        for (var dishCard : dishCards) {
            handbook.put(dishCard.card_id(), dishCard);
        }
    }

    DishCard getDishCard(int cardId) {
        return handbook.get(cardId);
    }
}
