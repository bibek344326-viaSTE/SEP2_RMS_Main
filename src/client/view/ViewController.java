package client.view;

import client.core.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ViewController {
    public void init (ViewModelFactory viewModelFactory, ViewHandler viewHandler) throws RemoteException, SQLException;

}
