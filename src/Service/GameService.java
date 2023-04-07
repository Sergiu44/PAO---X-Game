package Service;

import Model.Game;
import Model.GameVariant;
import Repository.GameRepository;

import java.util.List;
import java.util.UUID;

public class GameService {
    public GameRepository gameRepository;
    public GameService() {
        gameRepository = new GameRepository();
    }

    public void Add(Game newGame) {
        gameRepository.add(newGame);
    }

    public void AddVariant(UUID gameId, GameVariant game) { gameRepository.addVariant(gameId, game);}

    public Game GetById(UUID gameId) {
        return gameRepository.getById(gameId);
    }

    public List<Game> GetAll() {
        return gameRepository.getAll();
    }
}