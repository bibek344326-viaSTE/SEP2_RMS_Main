package server.networking;

import shared.networking.serverInterfaces.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.spec.RSAOtherPrimeInfo;

public class ServerManager implements Server {

    private LoginServer loginServer;
    private CreateAccountServer createAccountServer;
    private TableServer tableServer;
    private CustomerListServer customerListServer;
    private ChatServer chatServer;

    public ServerManager(LoginServer loginServer, CreateAccountServer createAccountServer, TableServer tableServer, CustomerListServer customerListServer, ChatServer chatServer) throws RemoteException {
        this.loginServer = loginServer;
        this.createAccountServer = createAccountServer;
        this.tableServer = tableServer;
        this.customerListServer = customerListServer;
        this.chatServer = chatServer;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public ChatServer getChatServer() throws RemoteException {
        return chatServer;
    }

    @Override
    public CreateAccountServer getCreateAccountServer() throws RemoteException {
        return createAccountServer;
    }

    @Override
    public CustomerListServer getCustomerListServer() throws RemoteException {
        return customerListServer;
    }

    @Override
    public LoginServer getLoginServer() throws RemoteException {
        return null;
    }

    @Override
    public TableServer getTableServer() throws RemoteException {
        return tableServer;
    }

    @Override
    public void startServer() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Server", this);
        System.out.println("Server started.....");
    }
}
