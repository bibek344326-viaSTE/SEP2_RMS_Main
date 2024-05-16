package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.networking.tables.TablesClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeListener;
import java.util.List;

public class TableViewModel {

    private ObservableList<SimpleTableViewModel> tableList;
    private TablesClient tablemodel;
    private ObjectProperty<SimpleTableViewModel> selectedTableProperty;
    private StringProperty errorLabel;
    private ViewState viewState;

    public TableViewModel(ModelFactory modelFactory, ViewState viewState) {
        this.tablemodel = modelFactory.getTableModel();
        this.tableList = FXCollections.observableArrayList();
        this.selectedTableProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;
        updateTableList();
    }

    public StringProperty getErrorProperty() {
        return errorLabel;
    }

    public ObservableList<SimpleTableViewModel> getTableList() {
        return tableList;
    }

    public void updateTableList() {
        tableList.clear();
        for (Table table : tablemodel.getTables()) {
            tableList.add(new SimpleTableViewModel(table));
        }
    }

    public void setSelected(SimpleTableViewModel table) {
        if (table == null) {
            viewState.setTablenumber(null);
            viewState.setCapacity(0);
            viewState.setStatus(false);
        } else {
            this.selectedTableProperty.set(table);
            viewState.setTablenumber(selectedTableProperty.get().getTableNameProperty().get());
            viewState.setCapacity(selectedTableProperty.get().getCapacityProperty().get());
            viewState.setStatus(false);
        }
    }

    public void deselect() {
        setSelected(null);
    }

    public void addNewTable() {
        int nextTableNumber = tableList.size() + 1;
        int capacity = 6;
        Table newTable = new Table("Table " + nextTableNumber, capacity); // Create a new table
        Request request = tablemodel.addTable(newTable);
        // Handle the response from adding the table if needed
        updateTableList();
    }


    public List<Table> getTables() {
        return tablemodel.getTables();
    }


    public Request reserveTable(Reservation reservation) {
        return tablemodel.reserveTable(reservation);
    }


    public Request removeReservation(int reservationId) {
        return tablemodel.removeReservation(reservationId);
    }


    public Request addTable(Table table) {
        return tablemodel.addTable(table);
    }


    public void updateTable(Table table, String tableName, int tableCapacity) {
        tablemodel.updateTable(table, tableName, tableCapacity);
    }


    public void addListener(String eventName, PropertyChangeListener listener) {
        tablemodel.addListener(eventName, listener);
    }


    public void removeListener(String eventName, PropertyChangeListener listener) {
        tablemodel.removeListener(eventName, listener);
    }
    public void remove(){
        tablemodel.removeReservation(selectedTableProperty.get().getCapacityProperty().get());
        updateTableList();
    }
}