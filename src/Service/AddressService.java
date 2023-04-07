package Service;

import Model.Address;
import Repository.AddressRepository;

import java.util.List;
import java.util.UUID;

public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService() {
        this.addressRepository = new AddressRepository();
    }

    public void Add(Address newAddress) {
        addressRepository.add(newAddress);
    }

    public Address getAddressById(UUID addressId) {
        return addressRepository.getById(addressId);
    }

    public List<Address> getAll() {
        return addressRepository.getAll();
    }
}