package Service;

import Model.Game;
import Model.GameVariant;
import Repository.AdminRepository;
import Repository.GameRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GameService {
    public GameRepository gameRepository;
    public AdminRepository adminRepository;
    public GameService() {
        gameRepository = new GameRepository();
        adminRepository = new AdminRepository();
    }

    public void Add(Game newGame, UUID adminId) throws SQLException {
        gameRepository.add(newGame);
        adminRepository.increaseGame(adminId);
    }

    public void AddVariant(UUID gameId, UUID gameVariantId, UUID adminId) throws SQLException { gameRepository.addVariant(gameId, gameVariantId); adminRepository.increaseGameVariant(adminId);}

    public Game GetById(UUID gameId) throws SQLException {
        return gameRepository.getById(gameId);
    }

    public List<Game> GetAll() throws SQLException {
        return gameRepository.getAll();
    }

    public void DeleteById(UUID gameId, UUID adminId) throws SQLException {
        gameRepository.deleteById(gameId);
        adminRepository.decreaseGame(adminId);
    }
}