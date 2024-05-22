package client.view.staff;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.menuItem.MenuItem;

public class SimpleMenuViewModel {
    private final StringProperty itemNameProperty;
    private final StringProperty TypeProperty;

    public SimpleMenuViewModel(MenuItem menu) {
        this.itemNameProperty = new SimpleStringProperty(menu.getMenuItemName());
        this.TypeProperty = new SimpleStringProperty(menu.getMenuItemType());
    }
    public StringProperty getItemNameProperty() {return itemNameProperty;}
    public StringProperty getTypeProperty() {return TypeProperty;}
}
