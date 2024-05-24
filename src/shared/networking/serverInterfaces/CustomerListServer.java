package shared.networking.serverInterfaces;

import shared.utils.user.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CustomerListServer extends Remote {
    void addCustomer(Customer customer) throws RemoteException;

    void updateCustomer(Customer customer, String oldUsername) throws RemoteException;

    void removeCustomer(Customer customer) throws RemoteException;

    Customer getCustomer(String username) throws RemoteException;

    ArrayList<Customer> getCustomers() throws RemoteException;
}
