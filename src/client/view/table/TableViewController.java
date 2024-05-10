package client.view.table;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class TableViewController implements ViewController {
   /* @FXML
    private TableView<SimpleTableViewModel> tableView;
    @FXML
    private TableColumn<SimpleTableViewModel, Number> tableNumberColumn;
    @FXML
    private TableColumn<SimpleTableViewModel, Number> capacityColumn;
    @FXML
    private TableColumn<SimpleTableViewModel, Boolean> statusColumn;
    @FXML
    private TableColumn<SimpleTableViewModel, String> reservedByColumn;*/
    @FXML
    private Button clearSelectedTableButton;
    @FXML
    private Button addNewTableButton;
    @FXML
    private Label errorLabel; // Added missing import
    @FXML
    private Button editTableDetailsButton;
    @FXML
    private Button deleteTableButton;
    private ViewHandler viewHandler;
    private  ViewModelFactory viewModelFactory;


    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
        /*this.tableViewModel = viewModelFactory.getTableViewModel();


        tableView.setItems(tableViewModel.getTableList());
        tableNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getTableNumberProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().getCapacityProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        //errorLabel.textProperty().bind(tableViewModel.getErrorProperty());

        tableViewModel.setSelected(null);
        tableViewModel.deselect();

        // Selecting item
        TableViewSelectionModel<SimpleTableViewModel> selectionModel = tableView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tableViewModel.setSelected(selectionModel.getSelectedItem());
            }
        });
    }*/



   /* @FXML
    private void clearSelectedTableButton(ActionEvent event) { // Corrected method signature
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void addNewTableButton(ActionEvent event) { // Corrected method signature
        tableViewModel.addNewTable();
    }

    public void updateTableDetailsButton(ActionEvent actionEvent) {
    }

    public void deleteTableButton(ActionEvent actionEvent) {
    }*/
}
