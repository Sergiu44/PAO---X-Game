package Service;

import Model.GameVariant;
import Repository.GameVariantRepository;

import java.util.List;
import java.util.UUID;

public class GameVariantService {
    public GameVariantRepository gameVariantRepository;
    public GameVariantService() {
        gameVariantRepository = new GameVariantRepository();
    }

    public void Add(GameVariant newGame) {
        gameVariantRepository.add(newGame);
    }

    public GameVariant GetById(UUID gameId) {
        return gameVariantRepository.getById(gameId);
    }

    public List<GameVariant> GetAll() {
        return gameVariantRepository.getAll();
    }
}