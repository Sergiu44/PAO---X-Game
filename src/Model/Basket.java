package Model;

import java.util.UUID;

public class Basket {
    private UUID basketId;
    private String title;
    private GameVariant[] gameVariants;

    public UUID getBasketId() {
        return basketId;
    }

    public void setBasketId(UUID basketId) {
        this.basketId = basketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GameVariant[] getGameVariants() {
        return gameVariants;
    }

    public void setGameVariants(GameVariant[] gameVariants) {
        this.gameVariants = gameVariants;
    }

    public Basket(UUID basketId, String title, GameVariant[] gameVariants) {
        this.basketId = basketId;
        this.title = title;
        this.gameVariants = gameVariants;
    }

    public Basket() {
        this.gameVariants = new GameVariant[]{};
    }
}
