package Repository;

import Database.DatabaseConnection;
import Model.Basket;
import Model.GameVariant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketRepository implements IRepository<Basket> {

    static Connection con = DatabaseConnection.getConnection();

    public Basket setResult (Basket basket, ResultSet rs) throws SQLException {
        basket.setBasketId(UUID.fromString(rs.getString("basketId")));
        basket.setTitle(rs.getString("title"));
        return basket;
    }
    @Override
    public void add(Basket basket) throws SQLException {
        String query = "insert into basket(basketId, " + "title) VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, basket.getBasketId().toString());
        statement.setString(2, basket.getTitle());
        statement.executeUpdate();
    }

    public void addVariant(String basketId, String gameVariantId) throws SQLException {
        String query = "insert into `basket-gamevariant-mapping`(id, " + "basketId, " + "gameVariantId) VALUES (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, UUID.randomUUID().toString());
        statement.setString(2, basketId);
        statement.setString(3, gameVariantId);
        statement.executeUpdate();
    }

    @Override
    public Basket getById(UUID basketId) throws SQLException {
        String query = "select * from basket where basketId = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, basketId.toString());
        Basket basket = new Basket();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        while(rs.next()) {
            check = true;
            basket = setResult(basket, rs);
        }
        if(check) {
            return basket;
        }
        throw new RuntimeException("Basket not found");
    }

    @Override
    public List<Basket> getAll() throws SQLException {
        String query = "select * from basket";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Basket> baskets = new ArrayList<Basket>();

        while(rs.next()) {
            Basket basket = new Basket();
            basket = setResult(basket, rs);
            baskets.add(basket);
        }
        return baskets;
    }

    public void update(String newTitle, UUID basketId) throws SQLException {
        String query = "UPDATE basket SET title = ? WHERE basketId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, newTitle);
        statement.setString(2, basketId.toString());
        statement.executeUpdate();
    }

    public void remove(String gameVariantId, UUID basketId) throws SQLException {
        try {
            String query = "DELETE FROM `basket-gamevariant-mapping` WHERE basketId = ? AND gameVariantId = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, basketId.toString());
            statement.setString(2, gameVariantId);
            statement.executeUpdate();
            System.out.println("Successful delete");
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while removing game variant from basket: " + e.getMessage());
        }
    }

    @Override
    public Basket deleteById(UUID basketId) throws SQLException {
        try {
            // Delete rows from basket-gamevariant-mapping table
            String mappingQuery = "DELETE FROM `basket-gamevariant-mapping` WHERE basketId = ?";
            PreparedStatement mappingStatement = con.prepareStatement(mappingQuery);
            mappingStatement.setString(1, basketId.toString());
            mappingStatement.executeUpdate();

            // Retrieve basket before deleting
            String basketQuery = "SELECT title, basketId FROM basket WHERE basketId = ?";
            PreparedStatement basketStatement = con.prepareStatement(basketQuery);
            basketStatement.setString(1, basketId.toString());
            ResultSet rs = basketStatement.executeQuery();

            Basket basket = null;
            if (rs.next()) {
                basket = new Basket(UUID.fromString(rs.getString("basketId")), rs.getString("title"), new ArrayList<GameVariant>());
            }

            // No need to delete the basket from the database

            if (basket == null) {
                throw new RuntimeException("Basket not found");
            } else {
                System.out.println("Operation successful");
                return basket;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while deleting basket: " + e.getMessage());
        }
    }
}