package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewModelFactory;
import client.core.ViewState;
import client.model.tables.TableModel;
import javafx.beans.property.*;
import shared.utils.table.Table;

import java.rmi.RemoteException;

public class UpdateTableViewModel {
    private TableModel tableModel;
    private ViewState viewState;
    private StringProperty tableNameProperty;
    private IntegerProperty capacityProperty;
    private BooleanProperty statusProperty;
    private StringProperty errorProperty;
    private StringProperty headerProperty;

    public UpdateTableViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.tableModel = modelFactory.getTableModel();
        this.viewState = viewState;
        this.tableNameProperty = new SimpleStringProperty();
        this.capacityProperty = new SimpleIntegerProperty();
        this.statusProperty = new SimpleBooleanProperty();
        this.errorProperty = new SimpleStringProperty();
        this.headerProperty = new SimpleStringProperty("Update Table Details");
    }

    public void reset() {
        if (viewState != null && viewState.getTablename() != null) {
            Table table = tableModel.getTables().stream()
                    .filter(t -> t.getTableName().equals(viewState.getTablename()))
                    .findFirst()
                    .orElse(null);
            if (table != null) {
                tableNameProperty.set(table.getTableName());
                capacityProperty.set(table.getTableCapacity());
            }
        } else {
            tableNameProperty.set("");
            capacityProperty.set(0);
            statusProperty.set(false);
        }
        errorProperty.set(null);
    }

    public StringProperty tableNameProperty() {
        return tableNameProperty;
    }

    public IntegerProperty capacityProperty() {
        return capacityProperty;
    }

    public BooleanProperty statusProperty() {
        return statusProperty;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

    public StringProperty headerProperty() {
        return headerProperty;
    }

    public boolean updateTable() {
        try {
            Table table = new Table(tableNameProperty.get(), capacityProperty.get());
            tableModel.updateTable(table, tableNameProperty.get(), capacityProperty.get());
            return true;
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    public boolean createTable() {
        try {
            Table table = new Table(tableNameProperty.get(), capacityProperty.get());
            tableModel.createTable(table);
            return true;
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }
}