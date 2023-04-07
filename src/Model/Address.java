package Model;

import java.util.UUID;

public class Address {
    private UUID addressId;
    private String address1;
    private String address2;
    private String postalCode;
    private String country;
    private String county;
    private String phoneNumber;


    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address() {};

    public Address(UUID addressId, String address1, String address2, String postalCode, String country, String county, String phoneNumber) {
        this.addressId = addressId;
        this.address1 = address1;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.country = country;
        this.county = county;
        this.phoneNumber = phoneNumber;
    }
}