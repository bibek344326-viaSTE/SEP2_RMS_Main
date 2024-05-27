package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffCustomerViewController implements ViewController {
    @FXML
    private TableView<SimpleCustomerViewModel> customerTableView;
    @FXML
    private TableColumn<SimpleCustomerViewModel, String> customerNameColumn;
    @FXML
    private TableColumn<SimpleCustomerViewModel, String> passwordColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;

    private ViewHandler viewHandler;
    private StaffCustomerViewModel customerViewModel;
    private Region root;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws SQLException, RemoteException {
        this.viewHandler = viewHandler;
        this.customerViewModel = viewModelFactory.getCustomerViewModel();
        this.root = root;

        // Bind table columns to properties in SimpleCustomerViewModel
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().getPasswordProperty());

        // Populate table with data from ViewModel
        customerTableView.setItems(customerViewModel.getCustomerList());

        customerViewModel.setSelected(null);
        customerViewModel.deselect();
    }
    public void reset(){
        customerViewModel.clear();
        customerTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void addEditButton() throws SQLException, RemoteException {
        customerViewModel.addEdit();
        viewHandler.openView("createNewCustomer");
    }

    @FXML
    private void back() throws SQLException, RemoteException {
        viewHandler.openView("connectionButtons");
    }

    public Region getRoot() {
        return root;
    }
}