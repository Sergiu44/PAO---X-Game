package Controller;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Model.Admin;
import Model.Basket;
import Model.GameVariant;
import Model.User;
import Service.AdminService;
import Service.BasketService;

public class BasketController {

    private final Scanner scanner = new Scanner(System.in);
    private final GameVariantController gameVariantController;
    private final BasketService basketService;

    public BasketController() {
        this.gameVariantController = new GameVariantController();
        this.basketService = new BasketService();

    }

    public Basket create() {
        System.out.println("Creating a new basket:");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the number of game variants: ");
        int numGameVariants = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        GameVariant[] gameVariants = new GameVariant[numGameVariants];
        for (int i = 0; i < numGameVariants; i++) {
            System.out.println("Game variant #" + (i+1) + ":");
            GameVariant gameVariant = gameVariantController.create();
            gameVariants[i] = gameVariant;
        }
        Basket basket = new Basket();
        basket.setBasketId(UUID.randomUUID());
        basket.setTitle(title);
        basket.setGameVariants(gameVariants);
        // Save the basket to the database or other storage mechanism
        return basket;
    }

    public void read(Basket basket) {
        System.out.println("Basket information:");
        // Retrieve the basket from the database or other storage mechanism
        System.out.println("Basket ID: " + basket.getBasketId());
        System.out.println("Title: " + basket.getTitle());
        System.out.println("Game Variants:");
        GameVariant[] gameVariants = basket.getGameVariants();
        if(gameVariants.length > 0) {
            for (int i = 0; i < gameVariants.length; i++) {
                GameVariant gameVariant = gameVariants[i];
                System.out.println("Game Variant #" + (i + 1) + ": " + gameVariant.getTitle());
            }
        } else {
            System.out.println("Your basket is empty");
        }
    }

    public void add() {
        Basket basket = create();
        basketService.Add(basket);
    }

    public void getById(UUID uuid) {
        Basket basket = basketService.GetById(uuid);
        read(basket);
    }

    public void getAll() {
        List<Basket> baskets = basketService.GetAll();
        for(Basket basket : baskets) {
            read(basket);
        }
    }
}