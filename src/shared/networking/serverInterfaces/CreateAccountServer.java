package shared.networking.serverInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CreateAccountServer extends Remote{
    String addUser(String username, String password, String userType) throws RemoteException;
}

