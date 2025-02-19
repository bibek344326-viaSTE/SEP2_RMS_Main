package client.model.customer;

import shared.utils.Subject;
import shared.utils.user.Customer;

import java.util.List;

public interface CustomerModel extends Subject {
    void addCustomer(Customer customer);

    void updateCustomer(Customer customer, String oldUsername);

    void removeCustomer(String customerid);

    Customer getCustomer(String username);

    List<Customer> getCustomers();

}
