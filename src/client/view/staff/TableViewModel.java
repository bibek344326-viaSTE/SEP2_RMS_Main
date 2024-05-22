package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.Reservation.ReservationModel;
import client.model.tables.TableModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.List;

public class TableViewModel {

    private ObservableList<SimpleTableViewModel> tableList;
    private TableModel tablemodel;
    private BooleanProperty tableStatus;
    private ObjectProperty<SimpleTableViewModel> selectedTableProperty;
    private StringProperty errorLabel;
    private ViewState viewState;
   private ReservationModel reservationModel;


    public TableViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.tablemodel = modelFactory.getTableModel();
        //this.reservationModel = ,modelFactory.
        this.tableList = FXCollections.observableArrayList();
        this.selectedTableProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;

        for (SimpleTableViewModel table : tableList) {
            System.out.println(table.getTableNameProperty() + "\n");
        }

        updateTableList();

//        List<Table> tablesList = tablemodel.getTables();
//        for (Table table : tablesList) {
//            System.out.println(table.getTableName());
//        }
    }

    public ObservableList<SimpleTableViewModel> getTableList() {
        return tableList;
    }

    public StringProperty getErrorProperty() {
        return errorLabel;
    }


    public void updateTableList() {
        tableList.clear();
        for (int i = 0; i < tablemodel.getTables().size(); i++) {
            tableList.add(new SimpleTableViewModel(tablemodel.getTables().get(i)));
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
        Request request = tablemodel.createTable(newTable);
        // Handle the response from adding the table if needed
        updateTableList();
    }


    public List<Table> getTables() {
        return (tablemodel.getTables());
    }


//    public Request reserveTable(Reservation reservation) {
//        return tablemodel.reserveTable(reservation.getTempTable(), tablemodel.getTables());
//    }


    public Request removeReservation(int reservationId) {
        return tablemodel.removeReservation(reservationId);
    }


    public Request addTable(Table table) {
        return tablemodel.createTable(table);
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

    public void remove() throws RemoteException {
        tablemodel.deleteTable(selectedTableProperty.get().getTableNameProperty().get());
        updateTableList();
    }
}