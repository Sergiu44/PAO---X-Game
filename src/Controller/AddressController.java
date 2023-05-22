package Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import Model.Address;
import Service.AddressService;

public class AddressController {

    private final Scanner scanner = new Scanner(System.in);
    private final AddressService addressService;

    public AddressController() {
        this.addressService = new AddressService();
    }

    public Address create() {
        System.out.println("Creating a new address:");
        System.out.print("Enter address line 1: ");
        String address1 = scanner.nextLine();
        System.out.print("Enter address line 2: ");
        String address2 = scanner.nextLine();
        System.out.print("Enter postal code: ");
        String postalCode = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        System.out.print("Enter county: ");
        String county = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        Address address = new Address();
        address.setAddressId(UUID.randomUUID());
        address.setAddress1(address1);
        address.setAddress2(address2);
        address.setPostalCode(postalCode);
        address.setCountry(country);
        address.setCounty(county);
        address.setPhoneNumber(phoneNumber);
        // Save the address to the database or other storage mechanism
        return address;
    }

    public void read(Address address) {
        System.out.println("Address information:");
        // Retrieve the address from the database or other storage mechanism
        System.out.println("Address ID: " + address.getAddressId());
        System.out.println("Address Line 1: " + address.getAddress1());
        System.out.println("Address Line 2: " + address.getAddress2());
        System.out.println("Postal Code: " + address.getPostalCode());
        System.out.println("Country: " + address.getCountry());
        System.out.println("County: " + address.getCounty());
        System.out.println("Phone Number: " + address.getPhoneNumber());
    }

    public void add() throws SQLException {
        Address address = create();
        addressService.Add(address);
    }

    public void getById(UUID uuid) throws SQLException {
        Address address = addressService.getAddressById(uuid);
        read(address);
    }

    public void getAll() throws SQLException {
        List<Address> addresses = addressService.getAll();
        for(Address address : addresses) {
            read(address);
        }
    }
}