package Service;

import Model.Wishlist;
import Repository.WishlistRepository;

import java.util.List;
import java.util.UUID;

public class WishlistService {
    public WishlistRepository wishlistRepository;
    public WishlistService() {
        wishlistRepository = new WishlistRepository();
    }

    public void Add(Wishlist wishlist) {
        wishlistRepository.add(wishlist);
    }

    public Wishlist GetById(UUID wishlistId) {
        return wishlistRepository.getById(wishlistId);
    }

    public List<Wishlist> GetAll() {
        return wishlistRepository.getAll();
    }
}