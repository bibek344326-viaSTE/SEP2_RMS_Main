package client.view;

import client.core.ViewModelFactory;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ViewController {
    public void init (ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException, SQLException;

}
