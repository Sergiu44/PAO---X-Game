package Service;

import Model.Customer;
import Model.User;
import Repository.*;

import java.util.List;
import java.util.UUID;

public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void Add(Customer customer) {
        customerRepository.add(customer);
    }

    public Customer GetById(UUID customerId) {
        return customerRepository.getById(customerId);
    }

    public List<User> GetAll() {
        return customerRepository.getAll();
    }
}