package server.database.customer;

import shared.utils.user.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    void createCustomer(Customer customer);

    void updateCustomer(Customer customer, String oldUsername);

    void removeCustomer(Customer customer);

    Customer getCustomer(String username);

    ArrayList<Customer> getCustomers();
}
