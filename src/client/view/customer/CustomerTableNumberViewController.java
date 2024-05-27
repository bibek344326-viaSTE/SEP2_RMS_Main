package client.view.customer;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class CustomerTableNumberViewController {
    private Region root;
    private ViewHandler viewHandler;
    private CustomerTableNumberViewModel customerTableNumberViewModel;

    public void init(ViewModelFactory viewModelFactory,ViewHandler viewHandler,Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.customerTableNumberViewModel = viewModelFactory.getCustomerTableNumberViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
