package Repository;

import Model.Address;
import Model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressRepository implements IRepository<Address> {
    List<Address> addresses = new ArrayList<>();

    @Override
    public void add(Address address) { addresses.add(address); }

    @Override
    public List<Address> getAll() { return addresses; }

    @Override
    public Address getById(UUID addressId)
    {
        for (Address address : addresses) {
            if (address.getAddressId().equals(addressId)) {
                return address;
            }
        }
        throw new RuntimeException("Address not found for id:" + addressId);
    }

    @Override
    public void deleteById(UUID addressId) {
        boolean isRemoved = addresses.removeIf(address -> address.getAddressId().equals(addressId));
        if (!isRemoved) {
            throw new RuntimeException("Address not found for id: " + addressId);
        }
    }
}