package Controller;

import java.sql.SQLException;
import java.util.*;

import Model.*;
import Repository.AdminRepository;
import Service.GameService;
import Service.GameVariantService;

public class GameController {

    private final Scanner scanner = new Scanner(System.in);
    private final GameVariantController gameVariantController;
    private final GameService gameService;
    private final GameVariantService gameVariantService;
    private final AdminRepository adminRepository;

    public GameController() {
        this.adminRepository = new AdminRepository();
        this.gameVariantController = new GameVariantController();
        this.gameService = new GameService();
        this.gameVariantService = new GameVariantService();
    }

    public Game create(UUID adminId) throws SQLException {
        System.out.println("Creating a new game:");
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
        System.out.println("Do you wish to add variants to this game?");
        String answer = scanner.nextLine();
        GameVariant[] gameVariants = {};
        if(answer.toLowerCase().equals("yes")) {
            System.out.print("Enter the number of game variants: ");
            int numGameVariants = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            gameVariants = new GameVariant[numGameVariants];
            for (int i = 0; i < numGameVariants; i++) {
                System.out.println("Game variant #" + (i + 1) + ":");
                GameVariant gameVariant = gameVariantController.create();
                adminRepository.increaseGameVariant(adminId);
                gameVariants[i] = gameVariant;
            }
        }
        Game game = new Game();
        game.setGameId(UUID.randomUUID());
        game.setTitle(title);
        game.setDescription(description);
        game.setPrice(price);
        game.setRrp(rrp);
        game.setCreateAt(createdAt);
        game.setGameVariants(gameVariants);
        return game;
    }

    public void read(Game game) {
        System.out.println("Game information:");
        System.out.println("Game ID: " + game.getGameId());
        System.out.println("Title: " + game.getTitle());
        System.out.println("Description: " + game.getDescription());
        System.out.println("Price: " + game.getPrice());
        System.out.println("RRP: " + game.getRrp());
        System.out.println("Created At: " + game.getCreateAt());
        GameVariant[] gameVariants = game.getGameVariants();
        if(gameVariants != null) {
            System.out.println("Game Variants:");
            for (int i = 0; i < gameVariants.length; i++) {
                GameVariant gameVariant = gameVariants[i];
                System.out.println("Game Variant #" + (i + 1) + ": " + gameVariant.getTitle());
            }
        } else {
            System.out.println("This game does not have game variants");
        }
    }

    public void add(UUID adminId) throws SQLException {
        Game game = create(adminId);
        gameService.Add(game, adminId);
    }

    public void addVariant(UUID gameId, UUID adminId) throws SQLException {
        GameVariant gameVariant = gameVariantController.create();
        gameVariantService.Add(gameVariant, adminId);
        gameService.AddVariant(gameId, gameVariant.getGameVariantId(), adminId);
    }

    public void getById(UUID uuid) throws SQLException {
        Game game = gameService.GetById(uuid);
        read(game);
    }

    public void getAll() throws SQLException {
        List<Game> games = gameService.GetAll();
        if(games.size() != 0) {
            for (Game game : games) {
                read(game);
            }
        } else {
            System.out.println("Currently there is no game available!");
        }
    }

    public void deleteById(UUID gameId, UUID adminId) throws SQLException {
        gameService.DeleteById(gameId, adminId);
    }
}