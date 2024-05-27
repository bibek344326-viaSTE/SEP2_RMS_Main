package server.model.customer;

import server.database.customer.CustomerDAO;
import server.database.customer.CustomerDAOManager;
import shared.utils.user.Customer;

import java.util.ArrayList;

public class CustomerListHandlerManager implements CustomerListHandler {

    private CustomerDAO customerDAO;

    public CustomerListHandlerManager() {
        customerDAO = new CustomerDAOManager();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer, String oldUsername) {
        customerDAO.updateCustomer(customer, oldUsername);
    }

    @Override
    public void removeCustomer(String customerid) {
        customerDAO.removeCustomer(customerid);
    }

    @Override
    public Customer getCustomer(String username) {
        return customerDAO.getCustomer(username);
    }

    @Override
    public ArrayList<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }
}
