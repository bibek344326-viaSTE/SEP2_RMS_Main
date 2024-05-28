package client.view.staff.MenuItems;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.menuItem.MenuItemModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.menuItem.MenuItem;

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

    public void clear(){
        errorLabel.set(null);
    }

    public ObservableList<SimpleMenuViewModel> getMenuItemList() {
        return menuItemList;
    }

    public StringProperty getErrorProperty() {
        return errorLabel;
    }

    public void updateMenuItemList() throws RemoteException, SQLException {
        menuItemList.clear();
        for (MenuItem menuItem : menuItemModel.getMenuItems()) {
            menuItemList.add(new SimpleMenuViewModel(menuItem));
        }
    }

    public void setSelected(SimpleMenuViewModel menuItem) {
        if (menuItem == null) {
            viewState.setMenuItemName(null);
            viewState.setMenuItemType(null);
            viewState.setId(0);
        } else {
            this.selectedMenuItemProperty.set(menuItem);
            viewState.setMenuItemName(menuItem.getItemNameProperty().get());
            viewState.setMenuItemType(menuItem.getTypeProperty().get());
            viewState.setId(menuItem.getItemIdProperty().get());
        }
    }

    public void deselect() {
        setSelected(null);
    }

    public void addEdit() throws SQLException, RemoteException {
        viewState.setRemove(false);
        SimpleMenuViewModel selectedMenuItem = selectedMenuItemProperty.get();
        if (selectedMenuItem != null) {
            viewState.setMenuItemName(selectedMenuItem.getItemNameProperty().get());
            viewState.setMenuItemType(selectedMenuItem.getTypeProperty().get());
            viewState.setId(selectedMenuItem.getItemIdProperty().get());
            errorLabel.set(null); // Clear error message if a menu item is selected
        } else {
            errorLabel.set("You have to select a menu item."); // Set error message if no menu item is selected
            viewState.setMenuItemName(null);
            viewState.setMenuItemType(null);
            viewState.setId(0);
        }
        updateMenuItemList();
    }

    public void remove() throws RemoteException, SQLException {
        if (selectedMenuItemProperty.get() != null) {
            menuItemModel.removeMenuItem(selectedMenuItemProperty.get().getItemIdProperty().get());
            updateMenuItemList();
            errorLabel.set(null); // Clear error message on success
        } else {
            errorLabel.set("You have to select a menu item to remove."); // Error if no menu item is selected
        }
    }
}