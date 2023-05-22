package Service;

import Model.Card;
import Repository.CardRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CardService {
    public CardRepository cardRepository;
    public CardService() {
        this.cardRepository = new CardRepository();
    }

    public void Add(Card newCard) throws SQLException {
        cardRepository.add(newCard);
    }

    public Card GetById(UUID cardId) throws SQLException {
        return cardRepository.getById(cardId);
    }

    public List<Card> GetAll() throws SQLException {
        return cardRepository.getAll();
    }
}