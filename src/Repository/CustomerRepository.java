package Repository;

import Model.Admin;
import Model.Customer;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerRepository extends UserRepository {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void add(User user) {
        if(!(user instanceof Customer)) {
            throw new IllegalArgumentException("[REPOSITORY-CUSTOMER]: Only customers can be added in here");
        }
        super.add(user);
        customers.add((Customer) user);
    }

    @Override
    public Customer getById(UUID customerId) {
        for (Customer customer : customers) {
            if (customer.getUserId().equals(customerId)) {
                return customer;
            }
        }
        throw new RuntimeException("[REPOSITORY-CUSTOMER]: Customer not found for id: " + customerId);
    }

    @Override
    public List<User> getAll() {
        List<User> allCustomers = super.getAll();
        List<User> customerOnly = new ArrayList<>();
        for (User user : allCustomers) {
            if (user instanceof Customer) {
                customerOnly.add(user);
            }
        }
        return customerOnly;
    }

    @Override
    public void deleteById(UUID customerId) {
        try {
            super.deleteById(customerId);
            customers.removeIf(customer -> customer.getUserId().equals(customerId));
        } catch (RuntimeException e) {
            throw new RuntimeException("[REPOSITORY-CUSTOMER]: Error deleting customer: " + e.getMessage());
        }
    }
}