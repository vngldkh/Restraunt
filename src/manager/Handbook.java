package manager;

import java.util.concurrent.ConcurrentHashMap;

public class Handbook {
    ConcurrentHashMap<Integer, DishCard> handbook = new ConcurrentHashMap<>();

    Handbook(DishCard[] dishCards) {
        for (var dishCard : dishCards) {
            handbook.put(dishCard.card_id(), dishCard);
        }
    }

    DishCard getDishCard(int cardId) {
        return handbook.get(cardId);
    }
}
