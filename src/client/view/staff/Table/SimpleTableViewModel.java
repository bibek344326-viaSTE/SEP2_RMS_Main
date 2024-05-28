package client.view.staff.Table;

import javafx.beans.property.*;
import shared.utils.table.Table;

public class SimpleTableViewModel {
    private final StringProperty tableNameProperty;
    private final IntegerProperty capacityProperty;
    private final BooleanProperty statusProperty;


    public SimpleTableViewModel(Table table) {
        this.tableNameProperty = new SimpleStringProperty(table.getTableName());
        this.capacityProperty = new SimpleIntegerProperty(table.getTableCapacity());
        this.statusProperty = new SimpleBooleanProperty(table.isOccupied());

    }

    public StringProperty getTableNameProperty() {
        return tableNameProperty;
    }

    public IntegerProperty getCapacityProperty() {
        return capacityProperty;
    }

    public BooleanProperty getStatusProperty() {
        return statusProperty;
    }

}
