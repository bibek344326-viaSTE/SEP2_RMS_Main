package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.menuItem.MenuItemModel;

import java.rmi.RemoteException;

public class AddNewMenuItemsViewModel {
    private MenuItemModel menuItemModel;
    private ViewState viewState;

    public AddNewMenuItemsViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        menuItemModel = modelFactory.getMenuItemModel();
        this.viewState = viewState;
    }
}
