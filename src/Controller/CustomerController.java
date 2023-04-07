package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Model.*;
import Service.CustomerService;

public class CustomerController {
    private final Scanner scanner;
    private final CardController cardController;
    private final AddressController addressController;
    private final WishlistController wishlistController;
    private final BasketController basketController;
    private final CustomerService customerService;

    public CustomerController() {
        this.scanner = new Scanner(System.in);
        this.cardController = new CardController();
        this.addressController = new AddressController();
        this.wishlistController = new WishlistController();
        this.basketController = new BasketController();
        this.customerService = new CustomerService();
    }

    public Customer create() {
        System.out.println("Creating customer...");

        System.out.print("Enter customer's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter customer's last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter customer's CNP: ");
        String cnp = scanner.nextLine();

        // Create cards for customer
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Address> addresses = new ArrayList<>();
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        Basket basket;
        int numCards = readInt("Enter number of cards to add: ");
        for (int i = 0; i < numCards; i++) {
            cards.add(cardController.create());
        }

        // Create addresses for customer
        int numAddresses = readInt("Enter number of addresses to add: ");
        for (int i = 0; i < numAddresses; i++) {
            addresses.add(addressController.create());
        }

        // Create wishlist for customer
        int numWishlists = readInt("Enter number of wishlists to add: ");
        for (int i = 0; i < numAddresses; i++) {
            wishlists.add(wishlistController.create());
        }

        // Create basket for customer
        basket = basketController.create();

        Customer customer = new Customer(UUID.randomUUID(), firstName, lastName, cnp, false, cards.toArray(new Card[cards.size()]), addresses.toArray(new Address[addresses.size()]), wishlists.toArray(new Wishlist[wishlists.size()]), basket);
        return customer;
    }

    public void read(Customer customer) {
        if (customer == null) {
            System.out.println("No customer selected.");
            return;
        }

        System.out.println("ID: " + customer.getUserId());
        System.out.println("First name: " + customer.getFirstName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("CNP: " + customer.getCNP());

        // Display cards for customer
        System.out.println("Cards:");
        for (Card card : customer.getCards()) {
            cardController.read(card);
        }

        // Display addresses for customer
        System.out.println("Addresses:");
        for (Address address : customer.getAddresses()) {
            addressController.read(address);
        }

        // Display wishlist for customer
        System.out.println("Wishlist:");
        for (Wishlist wishlist : customer.getWishlist()) {
            wishlistController.read(wishlist);
        }

        // Display basket for customer
        System.out.println("Basket:");
        basketController.read(customer.getBasket());
    }

    private int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(message);
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return num;
    }

    public Customer addDefault() {
        Customer customer = new Customer();
        customerService.Add(customer);
        return customer;
    }

    public Customer add() {
        Customer customer = create();
        customerService.Add(customer);
        return customer;
    }

    public void getById(UUID uuid) {
        Customer customer = customerService.GetById(uuid);
        read(customer);
    }

    public void showAll() {
        List<User> customers = customerService.GetAll();
        for(User customer : customers) {
            read((Customer)customer);
        }
    }

    public List<User> getAll() {
        return customerService.GetAll();
    }
}