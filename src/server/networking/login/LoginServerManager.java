package server.networking.login;

import server.model.login.LoginHandler;
import shared.networking.serverInterfaces.LoginServer;
import shared.utils.Request;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginServerManager implements LoginServer {
    private LoginHandler loginHandler;

    public LoginServerManager(LoginHandler loginHandler) throws RemoteException {
        this.loginHandler = loginHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public Request isLoginPossible(String username, String password) throws RemoteException {
        return loginHandler.login(username, password);
    }
}
