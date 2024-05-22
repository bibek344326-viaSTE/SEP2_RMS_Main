package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.Reservation.ReservationModel;
import client.model.tables.TableModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.Request;
import shared.utils.table.Table;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

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
        this.tableList = FXCollections.observableArrayList();
        this.selectedTableProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;

        updateTableList();
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
            viewState.setTablename(null);
            viewState.setCapacity(0);
            viewState.setStatus(false);
        } else {
            this.selectedTableProperty.set(table);
            viewState.setTablename(selectedTableProperty.get().getTableNameProperty().get());
            viewState.setCapacity(selectedTableProperty.get().getCapacityProperty().get());
            viewState.setStatus(selectedTableProperty.get().getStatusProperty().get());
        }
    }

    public void deselect() {
        setSelected(null);
    }

    public void addEdit() {
        SimpleTableViewModel selectedTable = selectedTableProperty.get();
        if (selectedTable != null) {
            viewState.setTablename(selectedTable.getTableNameProperty().get());
            viewState.setCapacity(selectedTable.getCapacityProperty().get());
            viewState.setStatus(selectedTable.getStatusProperty().get());
        } else {
            viewState.setTablename(null);
            viewState.setCapacity(0);
            viewState.setStatus(false);
        }
    }

    public Request removeReservation(int reservationId) {
        return tablemodel.removeReservation(reservationId);
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