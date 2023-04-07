package Controller;

import Model.GameVariant;
import Model.Wishlist;
import Service.WishlistService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class WishlistController {
    private final Scanner scanner = new Scanner(System.in);
    private final GameVariantController gameVariantController;
    private final WishlistService wishlistService;

    public WishlistController() {
        gameVariantController = new GameVariantController();
        this.wishlistService = new WishlistService();
    }

    public Wishlist create() {
        System.out.println("Creating a new wishlist:");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the number of games: ");
        int numGames = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        GameVariant[] games = new GameVariant[numGames];
        for (int i = 0; i < numGames; i++) {
            System.out.println("Game #" + (i+1) + ":");
            GameVariant game = gameVariantController.create();
            games[i] = game;
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(UUID.randomUUID());
        wishlist.setTitle(title);
        wishlist.setGameVariants(games);
        // Save the wishlist to the database or other storage mechanism
        return wishlist;
    }

    public void read(Wishlist wishlist) {
        System.out.println("Wishlist information:");
        System.out.println("Wishlist ID: " + wishlist.getWishlistId());
        System.out.println("Title: " + wishlist.getTitle());
        System.out.println("Games:");
        GameVariant[] games = wishlist.getGameVariants();
        for (int i = 0; i < games.length; i++) {
            GameVariant game = games[i];
            System.out.println("Game #" + (i+1) + ": " + game.getTitle());
        }
    }

    public void add() {
        Wishlist wishlist = create();
        wishlistService.Add(wishlist);
    }

    public void getById(UUID uuid) {
        Wishlist wishlist = wishlistService.GetById(uuid);
        read(wishlist);
    }

    public void getAll() {
        List<Wishlist> wishlists = wishlistService.GetAll();
        for(Wishlist wishlist : wishlists) {
            read(wishlist);
        }
    }
}