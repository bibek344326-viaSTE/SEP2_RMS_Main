package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AddNewCustomerController {
    private Region root;
    private ViewHandler viewHandler;
    private AddNewCustomerViewModel addNewCustomerViewModel;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.addNewCustomerViewModel = viewModelFactory.getAddNewCustomerViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
