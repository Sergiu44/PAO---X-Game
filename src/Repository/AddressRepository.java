package Repository;

import Database.DatabaseConnection;
import Model.Address;
import Model.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressRepository implements IRepository<Address> {
    static Connection con = DatabaseConnection.getConnection();

    public Address setResult (Address address, ResultSet rs) throws SQLException {
        address.setAddressId(UUID.fromString(rs.getString("addressId")));
        address.setAddress1(rs.getString("address1"));
        address.setAddress2(rs.getString("address2"));
        address.setPostalCode(rs.getString("postalCode"));
        address.setCountry(rs.getString("country"));
        address.setCounty(rs.getString("county"));
        address.setPhoneNumber(rs.getString("phoneNumber"));
        return address;
    }
    @Override
    public void add(Address address) throws SQLException {
        String query = "insert into address(addressId, " + "address1, " + "address2, " + "postalCode, " + "country, " + "county, " + "phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        UUID uuid = UUID.randomUUID();
        statement.setString(1, uuid.toString());
        statement.setString(2, address.getAddress1());
        statement.setString(3, address.getAddress2());
        statement.setString(4, address.getPostalCode());
        statement.setString(5, address.getCountry());
        statement.setString(6, address.getCounty());
        statement.setString(7, address.getPhoneNumber());
        statement.executeUpdate();
    }

    @Override
    public List<Address> getAll() throws SQLException {
        String query = "select * from address";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Address> addresses = new ArrayList<Address>();

        while(rs.next()) {
            Address address = new Address();
            address = setResult(address, rs);
            addresses.add(address);
        }
        return addresses;
    }

    @Override
    public Address getById(UUID addressId) throws SQLException {
        String query = "select * from address where addressId = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, addressId.toString());
        Address address = new Address();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        while(rs.next()) {
            check = true;
            address = setResult(address, rs);
        }
        if(check) {
            return address;
        }
        throw new RuntimeException("Address not found for id:" + addressId);
    }

    @Override
    public Basket deleteById(UUID addressId) throws SQLException {
        String query = "delete from address where addressId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, addressId.toString());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Address not found for id: " + addressId);
        }
        return null;
    }
}