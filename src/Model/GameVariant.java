package Model;

import java.util.Date;
import java.util.UUID;

public class GameVariant {
    private UUID gameVariantId;
    private String title;
    private String description;
    private Float price;
    private Float rrp;
    private Date createAt;

    public UUID getGameVariantId() {
        return gameVariantId;
    }

    public void setGameVariantId(UUID gameVariantId) {
        this.gameVariantId = gameVariantId;
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

    public GameVariant() {
    }

    public GameVariant(UUID gameVariantId, String title, String description, Float price, Float rrp, Date createAt) {
        this.gameVariantId = gameVariantId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rrp = rrp;
        this.createAt = createAt;
    }
}