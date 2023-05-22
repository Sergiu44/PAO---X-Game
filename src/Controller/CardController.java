package Controller;

import Model.Basket;
import Model.Card;
import Model.GameVariant;
import Service.CardService;

import java.sql.SQLException;
import java.util.*;

public class CardController {
    private final Scanner scanner;
    private final CardService cardService;

    public CardController() {
        this.scanner = new Scanner(System.in);
        this.cardService = new CardService();
    }

    public Card create() {
        System.out.println("Creating a new card:");
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter card holder name: ");
        String cardHolderName = scanner.nextLine();
        System.out.print("Enter expiration date: ");
        String expirationDate = scanner.nextLine();
        System.out.print("Enter CVV: ");
        String CVV = scanner.nextLine();
        System.out.print("Enter credit: ");
        Float credit = scanner.nextFloat();
        scanner.nextLine(); // consume the newline character
        Card card = new Card();
        card.setCardId(UUID.randomUUID());
        card.setCardNumber(cardNumber);
        card.setCardHolderName(cardHolderName);
        card.setExpirationDate(expirationDate);
        card.setCVV(CVV);
        card.setCredit(credit);
        // Save the card to the database or other storage mechanism
        return card;
    }

    public void read(Card card) {
        System.out.println("Card information:");
        // Display the card information
        System.out.println("Card ID: " + card.getCardId());
        System.out.println("Card number: " + card.getCardNumber());
        System.out.println("Card holder name: " + card.getCardHolderName());
        System.out.println("Expiration date: " + card.getExpirationDate());
        System.out.println("CVV: " + card.getCVV());
        System.out.println("Credit: " + card.getCredit());
    }

    public void add() throws SQLException {
        Card card = create();
        cardService.Add(card);
    }

    public void getById(UUID uuid) throws SQLException {
        Card card = cardService.GetById(uuid);
        read(card);
    }

    public void getAll() throws SQLException {
        List<Card> cards = cardService.GetAll();
        for(Card card : cards) {
            read(card);
        }
    }
}