package Controller;

import java.sql.SQLException;
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

        Card[] cards = {};
        Address[] addresses = {};
        Wishlist[] wishlists = {};
        Basket basket = basketController.create(UUID.randomUUID());
        System.out.println(basket.getTitle());

        return new Customer(UUID.randomUUID(), UUID.randomUUID(), firstName, lastName, cnp, cards, addresses, wishlists, basket);
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

    public Customer add() throws SQLException {
        Customer customer = create();
        customerService.Add(customer);
        return customer;
    }

    public User getById(String CNP) throws SQLException {
        return customerService.GetById(CNP);
    }

    public void showAll() throws SQLException {
        List<User> customers = customerService.GetAll();
        for(User customer : customers) {
            read((Customer)customer);
        }
    }

    public List<User> getAll() throws SQLException {
        return customerService.GetAll();
    }
}