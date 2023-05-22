package Model;

import java.util.UUID;

public final class Customer extends User {
    private UUID customerId;
    private Card[] cards;
    private Address[] addresses;
    private Wishlist[] wishlist;
    private Basket basket;

    public UUID getCustomerId() { return this.customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }
    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    public Wishlist[] getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist[] wishlist) {
        this.wishlist = wishlist;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Customer() {
        super(UUID.randomUUID(), "DEMO", "DEMO", "DEMO-CNP");
        this.customerId = UUID.randomUUID();
        this.cards = new Card[]{};
        this.addresses = new Address[]{};
        this.wishlist = new Wishlist[]{};
        this.basket = new Basket();
    }

    public Customer(UUID userId, UUID customerId, String firstName, String lastName, String CNP, Card[] cards, Address[] addresses, Wishlist[] wishlist, Basket basket) {
        super(userId, firstName, lastName, CNP);
        this.customerId = customerId;
        this.cards = cards;
        this.addresses = addresses;
        this.wishlist = wishlist;
        this.basket = basket;
    }
}
