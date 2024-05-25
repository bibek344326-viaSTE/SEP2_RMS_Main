package client.view.customer;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class CustomerViewMenuItemsController {
    private Region root;
    private CustomerViewMenuItemsViewModel customerViewMenuItemsViewModel;
    private ViewHandler viewHandler;

    public void init(ViewModelFactory viewModelFactory,ViewHandler viewHandler,Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.customerViewMenuItemsViewModel = viewModelFactory.getCustomerViewMenuItemsViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
