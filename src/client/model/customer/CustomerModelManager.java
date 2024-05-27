package client.model.customer;

import client.networking.customer.CustomerInfoClient;
import shared.utils.user.Customer;

import java.beans.PropertyChangeListener;
import java.util.List;

public class CustomerModelManager implements CustomerModel {

    private CustomerInfoClient client;

    public CustomerModelManager(CustomerInfoClient customerInfoClient) {
        this.client = customerInfoClient;
    }

    @Override
    public void addCustomer(Customer customer) {
        client.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer, String oldUsername) {
        client.updateCustomer(customer, oldUsername);
    }

    @Override
    public void removeCustomer(String customerid) {
        client.removeCustomer(customerid);
    }

    @Override
    public Customer getCustomer(String username) {
        return client.getCustomer(username);
    }

    @Override
    public List<Customer> getCustomers() {
        return client.getCustomers();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        addListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        removeListener(eventName, listener);
    }
}
