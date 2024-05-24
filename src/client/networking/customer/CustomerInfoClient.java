package client.networking.customer;

import shared.utils.user.Customer;
import java.util.List;

public interface CustomerInfoClient {
    void createCustomer(Customer customer);

    void updateCustomer(Customer customer, String oldUsername);
    void removeCustomer(Customer customer);
    Customer getCustomer(String username);
    List<Customer> getCustomers();
}
