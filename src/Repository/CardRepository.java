package Repository;

import Database.DatabaseConnection;
import Model.Basket;
import Model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository implements IRepository<Card>{
    static Connection con = DatabaseConnection.getConnection();

    public Card setResult (Card card, ResultSet rs) throws SQLException {
        card.setCardId(UUID.fromString(rs.getString("cardId")));
        card.setCardNumber(rs.getString("cardNumber"));
        card.setCardHolderName(rs.getString("cardholderName"));
        card.setExpirationDate(rs.getString("expirationDate"));
        card.setCVV(rs.getString("cvv"));
        card.setCredit(rs.getFloat("credit"));
        return card;
    }

    @Override
    public void add(Card card) throws SQLException {
        String query = "insert into card(cardId, " + "cardNumber, " + "cardholderName, " + "expirationDate, " + "cvv, " + "credit) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        UUID uuid = UUID.randomUUID();
        statement.setString(1, uuid.toString());
        statement.setString(2, card.getCardNumber());
        statement.setString(3, card.getCardHolderName());
        statement.setString(4, card.getExpirationDate());
        statement.setString(5, card.getCVV());
        statement.setFloat(6, card.getCredit());
        statement.executeUpdate();
    }

    @Override
    public Card getById(UUID cardId) throws SQLException {
        String query = "select * from card where cardId = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, cardId.toString());
        Card card = new Card();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        while(rs.next()) {
            check = true;
            card = setResult(card, rs);
        }
        if(check) {
            return card;
        }
        throw new RuntimeException("[REPOSITORY-CARD]: Card not found with id: " + cardId);
    }

    @Override
    public List<Card> getAll() throws SQLException {
        String query = "select * from card";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Card> cards = new ArrayList<Card>();

        while(rs.next()) {
            Card card = new Card();
            card = setResult(card, rs);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public Basket deleteById(UUID cardId) throws SQLException {
        String query = "delete from card where cardId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, cardId.toString());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[REPOSITORY-CARD]: Card not found with id: " + cardId);
        }
        return null;
    }
}