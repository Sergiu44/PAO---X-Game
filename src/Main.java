import Controller.*;
import Model.Admin;
import Model.Customer;
import Model.GameVariant;
import Model.User;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        AddressController addressController = new AddressController();
        AdminController adminController = new AdminController();
        BasketController basketController = new BasketController();
        CardController cardController = new CardController();
        CustomerController customerController = new CustomerController();
        GameController gameController = new GameController();
        GameVariantController gameVariantController = new GameVariantController();
        WishlistController wishlistController = new WishlistController();

        Scanner sc = new Scanner(System.in);
        String option;

        System.out.println("Welcome to X-Game Platform");

        while(true) {
            System.out.println("Choose what type of account you want to work with:\n1. Admin account\n2. Customer account\n3. Demo account\n4. Exit");
            option = sc.nextLine();
            switch (option) {
                case "1" -> {
                    if(adminController.getAll().size() == 0) {
                        System.out.println("It looks like you don't have an Admin account yet. Let's get started!\n");
                        User admin = adminController.add();
                    } else {
                        System.out.println("To get enter in our application you have to insert your CNP first.\n");
                        String CNP = sc.nextLine();
                        if(!CNP.equals(adminController.getAll().get(0).getCNP())) {
                            System.out.println("[ADMIN-CREDENTIALS]: Your credentials are not good. Bye-Bye");
                            return;
                        }
                        System.out.println("[ADMIN-CREDENTIALS]: Nice credentials. Welcome Admin!\n");
                        boolean isAdmin = true;
                        while(isAdmin) {
                            User admin = adminController.getAll().get(0);
                            System.out.println("Choose an option:\n1. Create a game\n2. Create a game variant\n3. See your account details\n4. See all games\n5. See all game variants\n6. Exit");
                            option = sc.nextLine();
                            switch (option) {
                                case "1" -> {
                                    gameController.add();
                                    break;
                                }
                                case "2" -> {
                                    System.out.println("You have to choose from those game:\n");
                                    gameController.getAll();
                                    System.out.println("Insert the id for which you want to create a game variant");
                                    UUID id = UUID.fromString(sc.nextLine());
                                    gameController.addVariant(id);
                                    break;
                                }
                                case "3" -> {
                                    adminController.getById(admin.getUserId());
                                    break;
                                }
                                case "4" -> {
                                    gameController.getAll();
                                    break;
                                }
                                case "5" -> {
                                    gameVariantController.getAll();
                                    break;
                                }
                                case "6" -> {
                                    System.out.println("Bye bye Admin");
                                    isAdmin = false;
                                    break;
                                }
                            }
                        }
                    }

                }
                case "2" -> {
                    if(customerController.getAll().size() == 0) {
                        System.out.println("It looks like you don't have an Customer account yet. Let's get started!\n");
                        User customer = customerController.add();
                    } else {
                        System.out.println("To get enter in our application you have to insert your CNP first.\n");
                        String CNP = sc.nextLine();
                        if(!CNP.equals(customerController.getAll().get(0).getCNP())) {
                            System.out.println("[CUSTOMER-CREDENTIALS]: Your credentials are not good. Bye-Bye");
                            return;
                        }
                        System.out.println("[CUSTOMER-CREDENTIALS]: Nice credentials. Welcome Customer!\n");
                        boolean isCustomer = true;
                        while(isCustomer) {
                            User customer = customerController.getAll().get(0);
                            System.out.println("Choose an option:\n1. See all games\n2. See all game variants\n3. Create basket\n4. Create wishlist\n5. Create card\n6. Create address\n7. Exit");
                            option = sc.nextLine();
                            switch (option) {
                                case "1" -> {
                                    gameController.getAll();
                                    break;
                                }
                                case "2" -> {
                                    gameVariantController.getAll();
                                    break;
                                }
                                case "3" -> {
                                    basketController.add();
                                    break;
                                }
                                case "4" -> {
                                    wishlistController.add();
                                    break;
                                }
                                case "5" -> {
                                    cardController.add();
                                    break;
                                }
                                case "6" -> {
                                    addressController.add();
                                    break;
                                }
                                case "7" -> {
                                    System.out.println("Bye bye");
                                    isCustomer = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                case "3" -> {
                    System.out.println("We will setup a DEMO account for you!\n");
                    User customer = customerController.addDefault();
                    while(true) {
                        System.out.println("Choose an option:\n1. See all games\n2. See all game variants\n3. See your account details\n4. Exit");
                        option = sc.nextLine();
                        switch (option) {
                            case "1" -> {
                                gameController.getAll();
                                break;
                            }
                            case "2" -> {
                                gameVariantController.getAll();;
                                break;
                            }
                            case "3" -> {
                                customerController.getById(customer.getUserId());
                                break;
                            }
                            case "4" -> {
                                System.out.println("Bye Bye");
                                return;
                            }
                        }
                    }
                }
                case "4" -> {
                    System.out.println("Thanks for using our app!");
                    return;
                }
                default -> {
                    System.out.println("Incorect key! Try again");
                }
            }
        }
    }
}