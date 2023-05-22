package Repository;

import Database.DatabaseConnection;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerRepository extends UserRepository {
    static Connection con = DatabaseConnection.getConnection();
    public Customer setResult (Customer customer, ResultSet rs) throws SQLException {
        super.setResult(customer, rs);
        customer.setCustomerId(UUID.fromString(rs.getString("customerId")));
        customer.setUserId(UUID.fromString(rs.getString("userId")));
//        customer.setBask(rs.getInt("gameVariantsCreated"));
        return customer;
    }

    @Override
    public void add(User user) throws SQLException {
        if(!(user instanceof Customer)) {
            throw new IllegalArgumentException("[REPOSITORY-CUSTOMER]: Only customers can be added in here");
        }
        super.add(user);
        String query = "insert into customer(customerId, " + "userId, " + "basketId) VALUES (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, ((Customer) user).getCustomerId().toString());
        statement.setString(2, user.getUserId().toString());
        statement.setString(3, ((Customer) user).getBasket().getBasketId().toString());
        statement.executeUpdate();
    }


    public User getByCNP(String CNP) throws SQLException {
        String customerQuery = "SELECT c.customerId AS customerId, u.firstName AS firstName, u.lastName AS lastName, c.basketId AS basketId, u.CNP AS CNP, u.userId AS userId " +
                "FROM customer c LEFT JOIN user u USING (userId) WHERE u.CNP = ?";
        PreparedStatement customerStatement = con.prepareStatement(customerQuery);

        customerStatement.setString(1, CNP);
        ResultSet customerResult = customerStatement.executeQuery();
        boolean check = false;
        Customer customer = new Customer();

        if (customerResult.next()) {
            check = true;

            String customerId = customerResult.getString("customerId");
            customer = setResult(customer, customerResult);

            String basketQuery = "SELECT b.title AS basketTitle, b.basketId AS basketId " +
                    "FROM customer c " +
                    "LEFT JOIN basket b ON c.basketId = b.basketId " +
                    "WHERE c.customerId = ?";
            PreparedStatement basketStatement = con.prepareStatement(basketQuery);
            basketStatement.setString(1, customerId);
            ResultSet basketResult = basketStatement.executeQuery();

            Basket basket = new Basket();
            ArrayList<GameVariant> gameVariants = new ArrayList<>();

            if (basketResult.next()) {
                basket.setTitle(basketResult.getString("basketTitle"));
                basket.setBasketId(UUID.fromString(basketResult.getString("basketId")));

                String gameVariantQuery = "SELECT gv.title AS titleGV, gv.price AS price, gv.createdAt AS createdAt " +
                        "FROM `basket-gamevariant-mapping` bgm " +
                        "LEFT JOIN gameVariant gv ON bgm.gameVariantId = gv.gameVariantId " +
                        "WHERE bgm.basketId = ?";
                PreparedStatement gameVariantStatement = con.prepareStatement(gameVariantQuery);
                gameVariantStatement.setString(1, basket.getBasketId().toString());
                ResultSet gameVariantResult = gameVariantStatement.executeQuery();

                while (gameVariantResult.next()) {
                    GameVariant gameVariant = new GameVariant();
                    gameVariant.setTitle(gameVariantResult.getString("titleGV"));
                    gameVariant.setPrice(gameVariantResult.getFloat("price"));
                    gameVariant.setCreateAt(gameVariantResult.getDate("createdAt"));
                    gameVariants.add(gameVariant);
                }
            }

            basket.setGameVariants(gameVariants);
            customer.setBasket(basket);
        }

        if (check) {
            return customer;
        }

        return new Customer();
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * from customer left join user USING (userId)";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<User> customers = new ArrayList<>();

        while(rs.next()) {
            Customer customer = new Customer();
            customer = setResult(customer, rs);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Basket deleteById(UUID customerId) throws SQLException {
        String query = "delete from admin where adminId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, customerId.toString());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[REPOSITORY-CUSTOMER]: Error deleting customer: " + e.getMessage());
        }
        return null;
    }
}