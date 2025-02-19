package server.model.customer;

import shared.utils.user.Customer;

import java.util.ArrayList;

public interface CustomerListHandler {
    void addCustomer(Customer customer);

    void updateCustomer(Customer customer, String oldUsername);



    void removeCustomer(String customerid);

    Customer getCustomer(String username);

    ArrayList<Customer> getCustomers();

}
