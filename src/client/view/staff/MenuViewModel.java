package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.menuItem.MenuItemModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.Request;
import shared.utils.menuItem.MenuItem;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuViewModel {

    private final ObservableList<SimpleMenuViewModel> menuItemList;
    private final MenuItemModel menuItemModel;
    private final ObjectProperty<SimpleMenuViewModel> selectedMenuItemProperty;
    private final StringProperty errorLabel;
    private final ViewState viewState;

    public MenuViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException, SQLException {
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
        } else {
            this.selectedMenuItemProperty.set(menuItem);
            viewState.setMenuItemName(selectedMenuItemProperty.get().getItemNameProperty().get());
            viewState.setMenuItemType(selectedMenuItemProperty.get().getTypeProperty().get());
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
        } else {
            viewState.setMenuItemName(null);
            viewState.setMenuItemType(null);
        }
    }

    public void remove() {
        try {
            MenuItem menuItem = menuItemModel.getMenuItem(
                    Integer.parseInt(selectedMenuItemProperty.get().getItemNameProperty().get())
            );
            menuItemModel.removeMenuItem(menuItem);
            updateMenuItemList();
        } catch (RemoteException | SQLException e) {
            errorLabel.set("Failed to remove menu item: " + e.getMessage());
        }
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