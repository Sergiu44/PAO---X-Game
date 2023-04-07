package Model;

import java.util.Date;
import java.util.UUID;

public class Game {
    private UUID gameId;
    private String title;
    private String description;
    private Float price;
    private Float rrp;
    private Date createAt;
    private GameVariant[] gameVariants;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRrp() {
        return rrp;
    }

    public void setRrp(Float rrp) {
        this.rrp = rrp;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public GameVariant[] getGameVariants() {
        return gameVariants;
    }

    public void setGameVariants(GameVariant[] gameVariants) {
        this.gameVariants = gameVariants;
    }

    public Game() {
    }

    public Game(UUID gameId, String title, String description, Float price, Float rrp, Date createAt, GameVariant[] gameVariants) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rrp = rrp;
        this.createAt = createAt;
        this.gameVariants = gameVariants;
    }
}