package Model;

import java.util.Date;
import java.util.UUID;

public class Wishlist {
    private UUID wishlistId;
    private String title;
    private GameVariant[] gameVariants;
    private Integer numberOfItems;
    private Date createAt;
    private Float totalPrice;

    public UUID getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(UUID wishlistId) {
        this.wishlistId = wishlistId;
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

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Wishlist() {
    }

    public Wishlist(UUID wishlistId, String title, GameVariant[] gameVariants, Integer numberOfItems, Date createAt, Float totalPrice) {
        this.wishlistId = wishlistId;
        this.title = title;
        this.gameVariants = gameVariants;
        this.numberOfItems = numberOfItems;
        this.createAt = createAt;
        this.totalPrice = totalPrice;
    }
}
