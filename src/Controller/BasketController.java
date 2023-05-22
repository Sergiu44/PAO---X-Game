package Controller;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private final BasketService basketService;

    public BasketController() {
        this.basketService = new BasketService();

    }

    public Basket create(UUID basketId) {
        System.out.println("Creating a new basket:");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        Basket basket = new Basket();
        basket.setBasketId(basketId);
        basket.setTitle(title);
        return basket;
    }

    public void read(Basket basket) {
        System.out.println("Basket information:");
        // Retrieve the basket from the database or other storage mechanism
        System.out.println("Basket ID: " + basket.getBasketId());
        System.out.println("Title: " + basket.getTitle());
        System.out.println("Game Variants:");
        ArrayList<GameVariant> gameVariants = basket.getGameVariants();
        if(gameVariants.size() > 0) {
            for (int i = 0; i < gameVariants.size(); i++) {
                GameVariant gameVariant = gameVariants.get(i);
                System.out.println("Game Variant #" + (i + 1) + ": " + gameVariant.getTitle());
            }
        } else {
            System.out.println("Your basket is empty");
        }
    }

    public void add(UUID basketId) throws SQLException {
        Basket basket = create(basketId);
        basketService.Add(basket);
    }

    public void addVariant(String basketId, String gameVariantId) throws SQLException {
        basketService.AddVariant(basketId, gameVariantId);
    }

    public void getById(UUID uuid) throws SQLException {
        Basket basket = basketService.GetById(uuid);
        read(basket);
    }

    public void getAll() throws SQLException {
        List<Basket> baskets = basketService.GetAll();
        for(Basket basket : baskets) {
            read(basket);
        }
    }

    public void update(String newTitle, UUID basketId) throws SQLException {
        basketService.Update(newTitle, basketId);
    }

    public void remove(String gameVariantId, UUID basketId) throws SQLException {
        basketService.Remove(gameVariantId, basketId);
    }

    public Basket deleteById(UUID basketId) throws SQLException {
        return basketService.DeleteById(basketId);
    }
}