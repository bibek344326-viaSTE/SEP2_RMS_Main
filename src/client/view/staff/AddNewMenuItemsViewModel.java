package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.menuItem.MenuItemModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.menuItem.MenuItem;


import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddNewMenuItemsViewModel {
    private final MenuItemModel menuItemModel;
    private final ViewState viewState;
    private final StringProperty foodname;
    private final StringProperty drinks;
    private final StringProperty errorProperty;
    private final StringProperty headerProperty;

    public AddNewMenuItemsViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.menuItemModel = modelFactory.getMenuItemModel();
        this.viewState = viewState;
        this.foodname = new SimpleStringProperty();
        this.drinks = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
        this.headerProperty = new SimpleStringProperty("Add New Menu Items");
    }

    public void reset() throws SQLException, RemoteException {
        if (viewState != null && viewState.getMenuItemName() != null) {
            MenuItem menuItem = menuItemModel.getMenuItems().stream()
                    .filter(m -> m.getMenuItemName().equals(viewState.getMenuItemName()))
                    .findFirst()
                    .orElse(null);
            if (menuItem != null) {
                foodname.set(menuItem.getMenuItemName());
                drinks.set(menuItem.getMenuItemType());
            }
        } else {
            foodname.set("");
            drinks.set("");
        }
        errorProperty.set(null);
    }

    public StringProperty foodnameProperty() {
        return foodname;
    }

    public StringProperty drinksProperty() {
        return drinks;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

    public StringProperty headerProperty() {
        return headerProperty;
    }

    public boolean updateMenuItem() {
        if (validateInput()) {
            try {
                MenuItem menuItem = new MenuItem(foodname.get(), drinks.get());
                menuItemModel.updateMenuItem(menuItem, foodnameProperty().get(), drinksProperty().get());
                return true;
            } catch (Exception e) {
                errorProperty.set("Error updating menu item: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean createMenuItem() {
        if (validateInput()) {
            try {
                MenuItem menuItem = new MenuItem(foodname.get(), drinks.get());
                menuItemModel.createMenuItem(menuItem);
                return true;
            } catch (RemoteException e) {
                errorProperty.set("Remote exception: " + e.getMessage());
            } catch (Exception e) {
                errorProperty.set("Error creating menu item: " + e.getMessage());
            }
        }
        return false;
    }

    private boolean validateInput() {
        if (foodname.get() == null || foodname.get().isEmpty()) {
            errorProperty.set("Food name cannot be empty");
            return false;
        }
        if (drinks.get() == null || drinks.get().isEmpty()) {
            errorProperty.set("Drink type cannot be empty");
            return false;
        }
        return true;
    }
}