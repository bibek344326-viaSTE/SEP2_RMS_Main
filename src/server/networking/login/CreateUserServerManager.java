package server.networking.login;

import server.model.createUser.CreateUserHandler;
import shared.networking.serverInterfaces.CreateAccountServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CreateUserServerManager implements CreateAccountServer {

    private CreateUserHandler createUserHandler;

    public CreateUserServerManager(CreateUserHandler createUserHandler) throws RemoteException {
        this.createUserHandler = createUserHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public String addUser(String username, String password, String userType) throws RemoteException {
        return createUserHandler.addUser(username, password, userType);
    }
}
