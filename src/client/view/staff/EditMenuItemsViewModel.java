package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.menuItem.MenuItemModel;

import java.rmi.RemoteException;

public class EditMenuItemsViewModel {
    private MenuItemModel menuItemModel;
    private ViewState viewState;

    public EditMenuItemsViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.menuItemModel = modelFactory.getMenuItemModel();
        this.viewState = viewState;
    }
}
