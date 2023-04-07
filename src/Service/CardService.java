package Service;

import Model.Card;
import Repository.CardRepository;

import java.util.List;
import java.util.UUID;

public class CardService {
    public CardRepository cardRepository;
    public CardService() {
        this.cardRepository = new CardRepository();
    }

    public void Add(Card newCard) {
        cardRepository.add(newCard);
    }

    public Card GetById(UUID cardId) {
        return cardRepository.getById(cardId);
    }

    public List<Card> GetAll() {
        return cardRepository.getAll();
    }
}