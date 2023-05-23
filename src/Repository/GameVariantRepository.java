package Repository;

import Database.DatabaseConnection;
import Model.Basket;
import Model.GameVariant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameVariantRepository implements IRepository<GameVariant> {
    static Connection con = DatabaseConnection.getConnection();

    public GameVariant setResult (GameVariant gameVariant, ResultSet rs) throws SQLException {
        gameVariant.setGameVariantId(UUID.fromString(rs.getString("gameVariantId")));
        gameVariant.setTitle(rs.getString("title"));
        gameVariant.setDescription(rs.getString("description"));
        gameVariant.setPrice(rs.getFloat("price"));
        gameVariant.setRrp(rs.getFloat("rrp"));
        gameVariant.setCreateAt(rs.getDate("createdAt"));
        return gameVariant;
    }

    @Override
    public void add(GameVariant gameVariant) throws SQLException {
        String query = "insert into gamevariant(gameVariantId, " + "title, " + "description, " + "price, " + "rrp, " + "createdAt) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, gameVariant.getGameVariantId().toString());
        statement.setString(2, gameVariant.getTitle());
        statement.setString(3, gameVariant.getDescription());
        statement.setFloat(4, gameVariant.getPrice());
        statement.setFloat(5, gameVariant.getRrp());
        statement.setDate(6, new Date(gameVariant.getCreateAt().getTime()));
        statement.executeUpdate();
    }

    @Override
    public List<GameVariant> getAll() throws SQLException {
        String query = "select * from gamevariant";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<GameVariant> gameVariants = new ArrayList<GameVariant>();

        while(rs.next()) {
            GameVariant gameVariant = new GameVariant();
            gameVariant = setResult(gameVariant, rs);
            gameVariants.add(gameVariant);
        }
        return gameVariants;
    }

    @Override
    public GameVariant getById(UUID id) throws SQLException {
        String query = "select * from gamevariant where gameVariantId = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, id.toString());
        GameVariant gameVariant = new GameVariant();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        while(rs.next()) {
            check = true;
            gameVariant = setResult(gameVariant, rs);
        }
        if(check) {
            return gameVariant;
        }
        throw new RuntimeException("[REPOSITORY-GAMEVARIANT]: Game variant not found with id: " + id);
    }

    @Override
    public Basket deleteById(UUID id) throws SQLException {
        String query = "delete from `game-gamevariant-mapping` where variantId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, id.toString());

        String query1 = "delete from gamevariant where gameVariantId = ?";
        PreparedStatement statement1 = con.prepareStatement(query1);
        statement1.setString(1, id.toString());
        try {
            statement.executeUpdate();
            statement1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[REPOSITORY-GAMEVARIANT]: Error deleting game variant: " + e.getMessage());
        }
        return null;
    }
}