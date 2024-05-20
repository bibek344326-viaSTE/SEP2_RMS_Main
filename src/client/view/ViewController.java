package client.view;

import client.core.ViewModelFactory;

import java.rmi.RemoteException;

public interface ViewController {
    public void init (ViewModelFactory viewModelFactory, ViewHandler viewHandler) throws RemoteException;

}
