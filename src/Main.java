import Controller.*;
import Model.Admin;
import Model.Customer;
import Model.GameVariant;
import Model.User;
import Utils.CSVWriter;

import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        AdminController adminController = new AdminController();
        BasketController basketController = new BasketController();
        CustomerController customerController = new CustomerController();
        GameController gameController = new GameController();
        GameVariantController gameVariantController = new GameVariantController();

        Scanner sc = new Scanner(System.in);
        String option;

        System.out.println("Welcome to X-Game Platform");

        while(true) {
            System.out.println("Choose what type of account you want to work with:\n1. Admin account\n2. Customer account\n3. Exit");
            option = sc.nextLine();
            switch (option) {
                case "1" -> {
                    System.out.println("Insert the CNP of the account with which you want to work: ");
                    option = sc.nextLine();
                    Admin admin = (Admin) adminController.getById(option);
                    if(adminController.getAll().size() == 0 || admin.getCNP().equals("DEMO-CNP")) {
                        System.out.println("It looks like you don't have an Admin account yet. Let's get started!\n");
                        CSVWriter.CSVFile("[ADMIN]: New insert");
                        adminController.add();
                    } else {
                        System.out.println("To enter in our application you have to insert your firstName and lastName first.");
                        System.out.println("First name: ");
                        String firstName = sc.nextLine();
                        System.out.println("Last name: ");
                        String lastName = sc.nextLine();
                        if(!firstName.equals(admin.getFirstName()) && !lastName.equals(admin.getLastName())) {
                            CSVWriter.CSVFile("[ADMIN]: Login credentials incorrect");
                            System.out.println("[ADMIN-CREDENTIALS]: Your credentials are not good. Bye-Bye");
                            return;
                        }
                        CSVWriter.CSVFile("[ADMIN]: Login done");
                        System.out.println("[ADMIN-CREDENTIALS]: Nice credentials. Welcome Admin!\n");
                        boolean isAdmin = true;
                        while(isAdmin) {
                            System.out.println("Choose an option:\n1. Create a game\n2. Create a game variant\n3. Delete a game\n4. Delete a game variant\n5. See your account details\n6. See all games\n7. See all game variants\n8. Exit");
                            option = sc.nextLine();
                            switch (option) {
                                case "1" -> {
                                    CSVWriter.CSVFile("[Game]: New insert");
                                    gameController.add(admin.getAdminId());
                                    break;
                                }
                                case "2" -> {
                                    CSVWriter.CSVFile("[GameVariant]: New insert");
                                    System.out.println("You have to choose from those game:\n");
                                    gameController.getAll();
                                    System.out.println("Insert the id for which you want to create a game variant");
                                    UUID gameId = UUID.fromString(sc.nextLine());
                                    gameController.addVariant(gameId, admin.getAdminId());
                                    break;
                                }
                                case "3" -> {
                                    CSVWriter.CSVFile("[Game]: Delete by id");
                                    System.out.println("Insert the ID of the game to remove");
                                    option = sc.nextLine();
                                    gameController.deleteById(UUID.fromString(option), admin.getAdminId());
                                    break;
                                }
                                case "4" -> {
                                    CSVWriter.CSVFile("[GameVariant]: Delete by id");
                                    System.out.println("Insert the id for which you want to create a game variant");
                                    UUID gameId = UUID.fromString(sc.nextLine());
                                    gameVariantController.deleteById(gameId, admin.getAdminId());
                                    break;
                                }
                                case "5" -> {
                                    CSVWriter.CSVFile("[ADMIN]: See details");
                                    adminController.read((Admin) adminController.getById(admin.getCNP()));
                                    break;
                                }
                                case "6" -> {
                                    CSVWriter.CSVFile("[Game]: See games");
                                    gameController.getAll();
                                    break;
                                }
                                case "7" -> {
                                    CSVWriter.CSVFile("[GameVariant]: See game variants");
                                    gameVariantController.getAll();
                                    break;
                                }
                                case "8" -> {
                                    CSVWriter.CSVFile("[GameVariant]: See game variants by price");
                                    System.out.println("Insert the maximum price for the game variants");
                                    Float price = Float.valueOf(sc.nextLine());
                                    gameVariantController.getByPrice(price);
                                    break;
                                }
                                case "9" -> {
                                    System.out.println("Bye bye Admin");
                                    isAdmin = false;
                                    break;
                                }
                            }
                        }
                    }

                }
                case "2" -> {
                    System.out.println("Insert the CNP of the account with which you want to work: ");
                    String customerId = sc.nextLine();
                    Customer customer = (Customer) customerController.getById(customerId);
                    if(customerController.getAll().size() == 0 || customer.getCNP().equals("DEMO-CNP")) {
                        CSVWriter.CSVFile("[Customer]: New insert");
                        System.out.println("It looks like you don't have an Customer account yet. Let's get started!\n");
                        customerController.add();
                    } else {
                        System.out.println("To enter in our application you have to insert your firstName and lastName first.");
                        System.out.println("First name: ");
                        String firstName = sc.nextLine();
                        System.out.println("Last name: ");
                        String lastName = sc.nextLine();
                        if(!firstName.equals(customer.getFirstName()) || !lastName.equals(customer.getLastName())) {
                            CSVWriter.CSVFile("[Customer]: Login credentials incorrect");
                            System.out.println("[CUSTOMER-CREDENTIALS]: Your credentials are not good. Bye-Bye");
                            return;
                        }
                        CSVWriter.CSVFile("[Customer]: Login correct");
                        System.out.println("[CUSTOMER-CREDENTIALS]: Nice credentials. Welcome Customer!\n");
                        boolean isCustomer = true;
                        while(isCustomer) {
                            System.out.println("Choose an option:\n1. See all games\n2. See all game variants\n3. Add to basket\n4. View Basket\n5. Update basket\n6. Remove variant from basket\n7. Exit");
                            option = sc.nextLine();
                            switch (option) {
                                case "1" -> {
                                    CSVWriter.CSVFile("[Game]: See games");
                                    gameController.getAll();
                                    break;
                                }
                                case "2" -> {
                                    CSVWriter.CSVFile("[GameVariant]: See game variants");
                                    gameVariantController.getAll();
                                    break;
                                }
                                case "3" -> {
                                    CSVWriter.CSVFile("[Basket]: Add to basket");
                                    System.out.println("Insert the ID of the gameVariant to add in the basket");
                                    option = sc.nextLine();
                                    basketController.addVariant(customer.getBasket().getBasketId().toString(), option);
                                    break;
                                }
                                case "4" -> {
                                    CSVWriter.CSVFile("[Basket]: View basket");
                                    basketController.read(customer.getBasket());
                                    break;
                                }
                                case "5" -> {
                                    CSVWriter.CSVFile("[Basket]: Update basket");
                                    System.out.println("Select the title for your new basket");
                                    option = sc.nextLine();
                                    basketController.update(option, customer.getBasket().getBasketId());
                                    customer = (Customer) customerController.getById(customerId);
                                    break;
                                }
                                case "6" -> {
                                    CSVWriter.CSVFile("[Basket]: Remove from basket");
                                    System.out.println("Insert the ID of the gameVariant to remove from the basket");
                                    option = sc.nextLine();
                                    basketController.remove(option, customer.getBasket().getBasketId());
                                    customer = (Customer) customerController.getById(customerId);
                                    break;
                                }
                                case "7" -> {
                                    CSVWriter.CSVFile("[Basket]: Delete basket");
                                    basketController.deleteById(customer.getBasket().getBasketId());
                                    customer = (Customer) customerController.getById(customerId);
                                    break;
                                }
                                case "9" -> {
                                    CSVWriter.CSVFile("[Customer]: Exit");
                                    System.out.println("Bye bye");
                                    isCustomer = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                case "3" -> {
                    CSVWriter.CSVFile("[User]: Exit application");
                    System.out.println("Thanks for using our app!");
                    return;
                }
                default -> {
                    CSVWriter.CSVFile("[User]: Inccorect key");
                    System.out.println("Incorect key! Try again");
                }
            }
        }
    }
}