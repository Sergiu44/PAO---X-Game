package Service;

import Model.GameVariant;
import Repository.AdminRepository;
import Repository.CustomerRepository;
import Repository.GameVariantRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GameVariantService {
    public GameVariantRepository gameVariantRepository;
    public AdminRepository adminRepository;
    public GameVariantService() {
        gameVariantRepository = new GameVariantRepository();
        adminRepository = new AdminRepository();
    }

    public void Add(GameVariant newGame, UUID adminId) throws SQLException {
        adminRepository.increaseGameVariant(adminId);
        gameVariantRepository.add(newGame);
    }

    public GameVariant GetById(UUID gameId) throws SQLException {
        return gameVariantRepository.getById(gameId);
    }

    public List<GameVariant> GetAll() throws SQLException {
        return gameVariantRepository.getAll();
    }

    public void DeleteById(UUID gameId, UUID adminId) throws SQLException {
        gameVariantRepository.deleteById(gameId);
        adminRepository.decreaseGameVariant(adminId);
    }
}