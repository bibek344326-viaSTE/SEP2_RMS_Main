package client.view.staff.MenuItems;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditMenuItemsController {
    private ViewHandler viewHandler;
    private EditMenuItemsViewModel editMenuItemsViewModel;
    private Region root;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.editMenuItemsViewModel = viewModelFactory.getEditMenuItemsViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
