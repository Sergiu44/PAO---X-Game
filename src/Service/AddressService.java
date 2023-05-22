package Service;

import Model.Address;
import Repository.AddressRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService() {
        this.addressRepository = new AddressRepository();
    }

    public void Add(Address newAddress) throws SQLException {
        addressRepository.add(newAddress);
    }

    public Address getAddressById(UUID addressId) throws SQLException {
        return addressRepository.getById(addressId);
    }

    public List<Address> getAll() throws SQLException {
        return addressRepository.getAll();
    }
}