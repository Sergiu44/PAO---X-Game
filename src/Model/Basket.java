package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Basket {
    private UUID basketId;
    private String title;
    private ArrayList<GameVariant> gameVariants;

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

    public ArrayList<GameVariant> getGameVariants() {
        return gameVariants;
    }

    public void setGameVariants(ArrayList<GameVariant> gameVariants) {
        this.gameVariants = gameVariants;
    }

    public Basket(UUID basketId, String title, ArrayList<GameVariant> gameVariants) {
        this.basketId = basketId;
        this.title = title;
        this.gameVariants = gameVariants;
    }

    public Basket() {
        this.basketId = UUID.randomUUID();
        this.title = "Default Basket";
        this.gameVariants = new ArrayList<>();
    }
}
