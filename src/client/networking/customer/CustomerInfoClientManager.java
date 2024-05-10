package client.networking.customer;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.user.Customer;

import java.rmi.RemoteException;
import java.util.List;

public class CustomerInfoClientManager implements CustomerInfoClient {
    private Server server;

    public CustomerInfoClientManager() {
        try {
            this.server = GetServer.getServerFromRmi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer, String oldUsername) {
        try {
            server.getCustomerListServer().updateCustomer(customer, oldUsername);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCustomer(Customer customer) {
        try {
            server.getCustomerListServer().removeCustomer(customer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomer(String username) {
        try {
            return server.getCustomerListServer().getCustomer(username);
        } catch (RemoteException e) {
            System.out.println("Cannot connect!");
            return null;
        }
    }

    @Override
    public List<Customer> getCustomers() {
        try {
            return server.getCustomerListServer().getCustomers();
        } catch (RemoteException e) {
            System.out.println("Cannot connect!");
            return null;
        }
    }
}
