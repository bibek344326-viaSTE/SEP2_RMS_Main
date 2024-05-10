package client.networking.createUser;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;

import java.rmi.RemoteException;

public class CreateUserClientManager implements CreateUserClient {

    private Server server;

    public CreateUserClientManager() {
        try {
            server = GetServer.getServerFromRmi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String addUser(String username, String password, String userType) {
        try {
            return server.getCreateAccountServer().addUser(username, password, userType);
        } catch (RemoteException e) {
            return "Cannot connect to server";
        }
    }
}
