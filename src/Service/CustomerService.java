package Service;

import Model.Customer;
import Model.User;
import Repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BasketRepository basketRepository;
    public CustomerService() {
        this.customerRepository = new CustomerRepository();
        this.basketRepository = new BasketRepository();
    }

    public void Add(Customer customer) throws SQLException {
        basketRepository.add(customer.getBasket());
        customerRepository.add(customer);
    }

    public User GetById(String CNP) throws SQLException {
        return customerRepository.getByCNP(CNP);
    }

    public List<User> GetAll() throws SQLException {
        return customerRepository.getAll();
    }
}