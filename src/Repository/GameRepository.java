package Repository;

import Database.DatabaseConnection;
import Model.Basket;
import Model.Game;
import Model.GameVariant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameRepository implements IRepository<Game> {
    static Connection con = DatabaseConnection.getConnection();
    public GameVariantRepository gameVariantRepository = new GameVariantRepository();

    public Game setResult (Game game, ResultSet rs) throws SQLException {
        game.setGameId(UUID.fromString(rs.getString("gameId")));
        game.setTitle(rs.getString("title"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getFloat("price"));
        game.setRrp(rs.getFloat("rrp"));
        game.setCreateAt(rs.getDate("createdAt"));
        return game;
    }
    @Override
    public void add(Game game) throws SQLException {
        String query = "insert into game(gameId, " + "title, " + "description, " + "price, " + "rrp, " + "createdAt) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, game.getGameId().toString());
        statement.setString(2, game.getTitle());
        statement.setString(3, game.getDescription());
        statement.setFloat(4, game.getPrice());
        statement.setFloat(5, game.getRrp());
        statement.setDate(6, new Date(game.getCreateAt().getTime()));
        statement.executeUpdate();
        for (GameVariant gameVariant : game.getGameVariants()) {
            gameVariantRepository.add(gameVariant);
            addVariant(game.getGameId(), gameVariant.getGameVariantId());
        }
    }
    public void addVariant(UUID gameId, UUID gameVariantId) throws SQLException {
        String query = "insert into `game-gamevariant-mapping`(id, " + "gameId, " + "variantId) VALUES (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, UUID.randomUUID().toString());
        statement.setString(2, gameId.toString());
        statement.setString(3, gameVariantId.toString());
        statement.executeUpdate();
    }

    @Override
    public List<Game> getAll() throws SQLException {
        String query = "select * from game";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Game> games = new ArrayList<Game>();

        while(rs.next()) {
            Game game = new Game();
            game = setResult(game, rs);
            games.add(game);
        }
        return games;
    }

    @Override
    public Game getById(UUID gameId) throws SQLException {
        String query = "select * from game where gameId = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, gameId.toString());
        Game game = new Game();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        while(rs.next()) {
            check = true;
            game = setResult(game, rs);
        }
        if(check) {
            return game;
        }
        throw new RuntimeException("[REPOSITORY-GAME]: Game not found with id: " + gameId);
    }

    @Override
    public Basket deleteById(UUID gameId) throws SQLException {
        String query = "delete from game where gameId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, gameId.toString());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[REPOSITORY-GAME]: Error deleting game: " + e.getMessage());
        };
        return null;
    }
}