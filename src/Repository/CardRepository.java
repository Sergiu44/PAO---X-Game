package Repository;

import Model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository implements IRepository<Card>{
    List<Card> cards = new ArrayList<>();

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public Card getById(UUID cardId) {
        for (Card card : cards) {
            if (card.getCardId().equals(cardId)) {
                return card;
            }
        }
        throw new RuntimeException("[REPOSITORY-CARD]: Card not found with id: " + cardId);
    }

    @Override
    public List<Card> getAll() {
        return cards;
    }

    @Override
    public void deleteById(UUID cardId) {
        boolean removed = cards.removeIf(card -> card.getCardId().equals(cardId));
        if (!removed) {
            throw new RuntimeException("[REPOSITORY-CARD]: Card not found with id: " + cardId);
        }
    }
}