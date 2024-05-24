package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffCustomerController implements ViewController {
    @FXML
    private TableView<SimpleCustomerViewModel> customerTableView;
    @FXML
    private TableColumn<SimpleCustomerViewModel, String> customerNameColumn;
    @FXML
    private TableColumn<SimpleCustomerViewModel, String> passwordColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> assignTableComboBox;

    private ViewHandler viewHandler;
    private CustomerViewModel customerViewModel;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) throws SQLException, RemoteException {
        this.viewHandler = viewHandler;
        this.customerViewModel = viewModelFactory.getCustomerViewModel();

        // Bind table columns to properties in SimpleCustomerViewModel
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().getPasswordProperty());

        // Populate table with data from ViewModel
        customerTableView.setItems(customerViewModel.getCustomerList());

        customerViewModel.setSelected(null);
        customerViewModel.deselect();
    }

    @FXML
    private void addNewCustomer() {
        // Add new customer logic
    }

    @FXML
    private void editCustomer() {
        // Edit customer logic
    }


    @FXML
    private void back() {
        viewHandler.openConnectionButtons();
    }
}