package shared.networking.serverInterfaces;

import shared.utils.Request;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginServer extends Remote {
    Request isLoginPossible(String username, String password) throws RemoteException;
}
