package client.view.staff.MenuItems;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.menuItem.MenuItem;

public class SimpleMenuViewModel {
    private final StringProperty itemNameProperty;
    private final StringProperty TypeProperty;
    private final IntegerProperty itemIdProperty;

    public SimpleMenuViewModel(MenuItem menu) {
        this.itemNameProperty = new SimpleStringProperty(menu.getMenuItemName());
        this.TypeProperty = new SimpleStringProperty(menu.getMenuItemType());
        this.itemIdProperty = new SimpleIntegerProperty(menu.getMenuItemID());
    }
    public StringProperty getItemNameProperty() {return itemNameProperty;}
    public StringProperty getTypeProperty() {return TypeProperty;}
    public IntegerProperty getItemIdProperty() {return itemIdProperty;}
}
