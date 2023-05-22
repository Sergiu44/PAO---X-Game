package Service;

import Model.GameVariant;
import Repository.GameVariantRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GameVariantService {
    public GameVariantRepository gameVariantRepository;
    public GameVariantService() {
        gameVariantRepository = new GameVariantRepository();
    }

    public void Add(GameVariant newGame) throws SQLException {
        gameVariantRepository.add(newGame);
    }

    public GameVariant GetById(UUID gameId) throws SQLException {
        return gameVariantRepository.getById(gameId);
    }

    public List<GameVariant> GetAll() throws SQLException {
        return gameVariantRepository.getAll();
    }
}