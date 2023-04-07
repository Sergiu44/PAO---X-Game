package Repository;

import Model.Game;
import Model.GameVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameRepository implements IRepository<Game> {
    List<Game> games = new ArrayList<>();

    @Override
    public void add(Game game) {
        games.add(game);
    }
    public void addVariant(UUID gameId, GameVariant gameVariant) {
        Game game = getById(gameId);
        GameVariant[] gameVariants = new GameVariant[game.getGameVariants().length+1];
        for(int i = 0 ; i < game.getGameVariants().length; ++i) {
            gameVariants[i] = game.getGameVariants()[i];
        }
        gameVariants[game.getGameVariants().length] = gameVariant;
        game.setGameVariants(gameVariants);
    }

    @Override
    public List<Game> getAll() {
        return games;
    }

    @Override
    public Game getById(UUID gameId) {
        for (Game game : games) {
            if (game.getGameId().equals(gameId)) {
                return game;
            }
        }
        throw new RuntimeException("[REPOSITORY-GAME]: Game not found with id: " + gameId);
    }

    @Override
    public void deleteById(UUID gameId) {
        try {
            games.removeIf(admin -> admin.getGameId().equals(gameId));
        } catch (RuntimeException e) {
            throw new RuntimeException("[REPOSITORY-GAME]: Error deleting game: " + e.getMessage());
        };
    }
}