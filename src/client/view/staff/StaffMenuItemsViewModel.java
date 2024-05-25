package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.menuItem.MenuItemModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.menuItem.MenuItem;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffMenuItemsViewModel {

    private final ObservableList<SimpleMenuViewModel> menuItemList;
    private final MenuItemModel menuItemModel;
    private final ObjectProperty<SimpleMenuViewModel> selectedMenuItemProperty;
    private final StringProperty errorLabel;
    private final ViewState viewState;

    public StaffMenuItemsViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException, SQLException {
        this.menuItemModel = modelFactory.getMenuItemModel();
        this.menuItemList = FXCollections.observableArrayList();
        this.selectedMenuItemProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;

        updateMenuItemList();
    }

    public ObservableList<SimpleMenuViewModel> getMenuItemList() {
        return menuItemList;
    }

    public StringProperty getErrorProperty() {
        return errorLabel;
    }

    public void updateMenuItemList() throws RemoteException, SQLException {
        menuItemList.clear();
    for (int i = 0; i < menuItemModel.getMenuItems().size(); i++){
        menuItemList.add(new SimpleMenuViewModel(menuItemModel.getMenuItems().get(i)));
    }
    }

    public void setSelected(SimpleMenuViewModel menuItem) {
        if (menuItem == null) {
            viewState.setMenuItemName(null);
            viewState.setMenuItemType(null);
            viewState.setId(0);
        } else {
            this.selectedMenuItemProperty.set(menuItem);
            viewState.setMenuItemName(selectedMenuItemProperty.get().getItemNameProperty().get());
            viewState.setMenuItemType(selectedMenuItemProperty.get().getTypeProperty().get());
            viewState.setId(selectedMenuItemProperty.get().getItemIdProperty().get());
        }
    }

    public void deselect() {
        setSelected(null);
    }

    public void addEdit() {
        SimpleMenuViewModel selectedMenuItem = selectedMenuItemProperty.get();
        if (selectedMenuItem != null) {
            viewState.setMenuItemName(selectedMenuItem.getItemNameProperty().get());
            viewState.setMenuItemType(selectedMenuItem.getTypeProperty().get());
            viewState.setId(selectedMenuItem.getItemIdProperty().get());
        } else {
            viewState.setMenuItemName(null);
            viewState.setMenuItemType(null);
            viewState.setId(0);
        }
    }

    public void remove() throws RemoteException, SQLException {
        menuItemModel.removeMenuItem(selectedMenuItemProperty.get().getItemIdProperty().get());
        updateMenuItemList();

    }

    public void updateMenuItem(MenuItem menuItem, String newName, String newType) throws RemoteException, SQLException {
        menuItemModel.updateMenuItem(menuItem, newName, newType);
        updateMenuItemList();
    }

    public void addListener(String eventName, PropertyChangeListener listener) {
        menuItemModel.addListener(eventName, listener);
    }

    public void removeListener(String eventName, PropertyChangeListener listener) {
        menuItemModel.removeListener(eventName, listener);
    }
}