package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.utils.table.Table;

import java.rmi.RemoteException;
import java.util.List;

public class TableViewController implements ViewController {
    @FXML
    private TableView<SimpleTableViewModel> tableView;
    @FXML
    private TableColumn<SimpleTableViewModel, String> tableNameColumn;
    @FXML
    private TableColumn<SimpleTableViewModel, Number> capacityColumn;
    @FXML
    private TableColumn<SimpleTableViewModel, Boolean> statusColumn;
    @FXML
    private TableColumn<SimpleTableViewModel, String> reservedByColumn;
    @FXML
    private Button clearSelectedTableButton;
    @FXML
    private Button addNewTableButton;
    @FXML
    private Label errorLabel; // Added missing import
    @FXML
    private Button editTableDetailsButton;
    private ViewHandler viewHandler;
    private TableViewModel tableViewModel;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) throws RemoteException {
        this.viewHandler = viewHandler;
        this.tableViewModel = viewModelFactory.getTableViewModel();
        tableNameColumn.setCellValueFactory(cellData -> cellData.getValue().getTableNameProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().getCapacityProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        tableView.setItems(tableViewModel.getTableList());

        // Set up cell factory for status column
        statusColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Occupied" : "Vacant");
                }
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tableViewModel.setSelected(newSelection);
            }
        });

        errorLabel.textProperty().bind(tableViewModel.getErrorProperty());
    }

    public void reset() {
        tableViewModel.clear();
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void deleteTableButton() throws RemoteException {
       tableViewModel.remove();
    }

    @FXML
    private void clearSelectedTableButton(ActionEvent event) {
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void addEditButton(ActionEvent event) {
        tableViewModel.addEdit();
        viewHandler.openAddEditTable();
    }

    @FXML
    private void back() {
        viewHandler.openConnectionButtons();
    }
}