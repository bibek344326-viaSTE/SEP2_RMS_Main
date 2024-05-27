package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class CreateNewCustomerController {
    private ViewHandler viewHandler;
    private Region root;
    private CreateNewCustomerViewModel createNewCustomerViewModel;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.createNewCustomerViewModel = viewModelFactory.getCreateNewCustomerViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
