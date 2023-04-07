package Repository;

import Model.Game;
import Model.GameVariant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameVariantRepository implements IRepository<GameVariant> {
    List<GameVariant> gameVariants = new ArrayList<>();

    @Override
    public void add(GameVariant gameVariant) {
        gameVariants.add(gameVariant);
    }

    @Override
    public List<GameVariant> getAll() {
        return gameVariants;
    }

    @Override
    public GameVariant getById(UUID id) {
        for (GameVariant gameVariant : gameVariants) {
            if (gameVariant.getGameVariantId().equals(id)) {
                return gameVariant;
            }
        }
        throw new RuntimeException("[REPOSITORY-GAMEVARIANT]: Game variant not found with id: " + id);
    }

    @Override
    public void deleteById(UUID id) {
        try {
            gameVariants.removeIf(gameVariant -> gameVariant.getGameVariantId().equals(id));
        } catch (RuntimeException e) {
            throw new RuntimeException("[REPOSITORY-GAMEVARIANT]: Error deleting game variant: " + e.getMessage());
        }
    }
}