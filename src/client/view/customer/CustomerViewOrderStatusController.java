package client.view.customer;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class CustomerViewOrderStatusController {
    @FXML
    private TableView<SimpleCustomerOrderViewModel> orderTableView;
    @FXML
    private TableColumn<SimpleCustomerOrderViewModel, String> itemNameColumn;
    @FXML
    private TableColumn<SimpleCustomerOrderViewModel, String> typeColumn;
    @FXML
    private TableColumn<SimpleCustomerOrderViewModel, String> statusColumn;
    @FXML
    private Label customerName;
    @FXML
    private Label tableNumber;

    private ViewHandler viewHandler;
    private CustomerViewOrderStatusViewModel customerViewOrderStatusViewModel;
    private Region root;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.customerViewOrderStatusViewModel = viewModelFactory.getCustomerViewOrderStatusViewModel();

        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFoodnameProperty());
        //typeColumn.setCellValueFactory(cellData -> cellData.getValue().getFoodTypeProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());

        orderTableView.setItems(customerViewOrderStatusViewModel.getList());
        orderTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                customerViewOrderStatusViewModel.setSelected(newSelection);
            }
        });


    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void addToList() {
        // Add the selected item to the list logic
    }

    @FXML
    private void back() {
        // Navigate back logic
    }
}