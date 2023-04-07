package Repository;

import Model.Wishlist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WishlistRepository implements IRepository<Wishlist> {
    List<Wishlist> wishlists = new ArrayList<>();

    @Override
    public void add(Wishlist wishlist) {
        wishlists.add(wishlist);
    }

    @Override
    public Wishlist getById(UUID basketId) {
        for (Wishlist basket : wishlists) {
            if (basket.getWishlistId().equals(basketId)) {
                return basket;
            }
        }
        throw new RuntimeException("Basket not found");
    }

    @Override
    public List<Wishlist> getAll() {
        return wishlists;
    }

    @Override
    public void deleteById(UUID basketId) {
        boolean removed = wishlists.removeIf(basket -> basket.getWishlistId().equals(basketId));
        if (!removed) {
            throw new RuntimeException("Basket not found");
        }
    }
}