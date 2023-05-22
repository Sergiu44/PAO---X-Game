package Controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import Model.GameVariant;
import Service.GameVariantService;

public class GameVariantController {

    private final Scanner scanner = new Scanner(System.in);
    private final GameVariantService gameVariantService;

    public GameVariantController() {
        this.gameVariantService = new GameVariantService();
    }

    public GameVariant create() {
        System.out.println("Creating a new game variant:");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter price: ");
        Float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Enter RRP: ");
        Float rrp = scanner.nextFloat();
        scanner.nextLine();
        Date createdAt = new Date();
        GameVariant gameVariant = new GameVariant();
        gameVariant.setGameVariantId(UUID.randomUUID());
        gameVariant.setTitle(title);
        gameVariant.setDescription(description);
        gameVariant.setPrice(price);
        gameVariant.setRrp(rrp);
        gameVariant.setCreateAt(createdAt);
        return gameVariant;
        // Save the game variant to the database or other storage mechanism
    }

    public void read(GameVariant gameVariant) {
        System.out.println("Game variant information:");
        // Retrieve the game variant from the database or other storage mechanism
        System.out.println("Game Variant ID: " + gameVariant.getGameVariantId());
        System.out.println("Title: " + gameVariant.getTitle());
        System.out.println("Description: " + gameVariant.getDescription());
        System.out.println("Price: " + gameVariant.getPrice());
        System.out.println("RRP: " + gameVariant.getRrp());
        System.out.println("Created At: " + gameVariant.getCreateAt());
    }

    public void add() throws SQLException {
        GameVariant gameVariant = create();
        gameVariantService.Add(gameVariant);
    }

    public void getById(UUID uuid) throws SQLException {
        GameVariant gameVariant = gameVariantService.GetById(uuid);
        read(gameVariant);
    }

    public void getAll() throws SQLException {
        List<GameVariant> games = gameVariantService.GetAll();
        if(games.size() != 0) {
            for (GameVariant game : games) {
                read(game);
            }
        } else {
            System.out.println("Currently there is no game variant available!");
        }
    }
}