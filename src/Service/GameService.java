package Service;

import Model.Game;
import Model.GameVariant;
import Repository.GameRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GameService {
    public GameRepository gameRepository;
    public GameService() {
        gameRepository = new GameRepository();
    }

    public void Add(Game newGame) throws SQLException {
        gameRepository.add(newGame);
    }

    public void AddVariant(UUID gameId, UUID gameVariantId) throws SQLException { gameRepository.addVariant(gameId, gameVariantId);}

    public Game GetById(UUID gameId) throws SQLException {
        return gameRepository.getById(gameId);
    }

    public List<Game> GetAll() throws SQLException {
        return gameRepository.getAll();
    }
}