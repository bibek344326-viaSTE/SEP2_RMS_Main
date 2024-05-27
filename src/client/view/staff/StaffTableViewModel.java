package client.view.staff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.tables.TableModel;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.table.Table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffTableViewModel implements PropertyChangeListener {

    private ObservableList<SimpleTableViewModel> tableList;
    private TableModel tableModel;
    private ObjectProperty<SimpleTableViewModel> selectedTableProperty;
    private StringProperty errorLabel;
    private ViewState viewState;


    public StaffTableViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.tableModel = modelFactory.getTableModel();
        this.tableList = FXCollections.observableArrayList();
        this.selectedTableProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        this.viewState = viewState;
        updateTableList();
    }

    public ObservableList<SimpleTableViewModel> getTableList() {
        return tableList;
    }

    public void clear() {
        errorLabel.set(null);
    }

    public StringProperty getErrorProperty() {
        return errorLabel;
    }

    public void updateTableList() {
        tableList.clear();
        for (Table table : tableModel.getTables()) {
            tableList.add(new SimpleTableViewModel(table));
        }
    }

    public void setSelected(SimpleTableViewModel table) {
        if (table == null) {
            viewState.setTablename(null);
            viewState.setCapacity(0);
            viewState.setStatus(false);
        } else {
            this.selectedTableProperty.set(table);
            viewState.setTablename(table.getTableNameProperty().get());
            viewState.setCapacity(table.getCapacityProperty().get());
            viewState.setStatus(table.getStatusProperty().get());
        }
    }

    public void deselect() {
        setSelected(null);
    }

    public void addEdit() {
        viewState.setRemove(false);
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
        updateTableList();
    }

    public void remove() throws RemoteException, SQLException {
        tableModel.deleteTable(selectedTableProperty.get().getTableNameProperty().get());
        updateTableList();
    }
    public void changeStatus(){

    }

    private void removeSimpleTable(String tableName) {
        tableList.removeIf(simpleTableViewModel -> simpleTableViewModel.getTableNameProperty().get().equals(tableName));
    }

    private void addSimpleTable(Table table) {
        for (int i = 0; i < tableList.size(); i++) {
            Table t = tableModel.getTables().get(i);
            if (table.getTableCapacity() < t.getTableCapacity()) {
                tableList.add(i, new SimpleTableViewModel(table));
                return;
            }
        }
        tableList.add(new SimpleTableViewModel(table));
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "AddTable":
                    addSimpleTable((Table) event.getNewValue());
                    break;
                case "RemoveTable":
                    removeSimpleTable((String) event.getOldValue());
                    break;
                case "UpdateTable":
                    removeSimpleTable((String) event.getOldValue());
                    addSimpleTable((Table) event.getNewValue());
                    break;
            }
        });
    }
}