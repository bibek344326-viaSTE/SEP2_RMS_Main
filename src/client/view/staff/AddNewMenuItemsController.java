package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AddNewMenuItemsController {
    private ViewHandler viewHandler;
    private AddNewMenuItemsViewModel addNewMenuItemsViewModel;
    private Region root;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.addNewMenuItemsViewModel = viewModelFactory.getAddNewMenuItemsViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
