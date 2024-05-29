package client.view.kitchenstaff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import shared.utils.order.Order;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class KitchenStaffViewController implements ViewController {
    @FXML
    private TableView<SimpleKitchenViewModel> tableView;
    @FXML
    private TableColumn<SimpleKitchenViewModel, Number> orderIDColumn;
    @FXML
    private TableColumn<SimpleKitchenViewModel, String> tablenameColumn;
    @FXML
    private TableColumn<SimpleKitchenViewModel, String> customerNameColumn;
    @FXML
    private TableColumn<SimpleKitchenViewModel, String> ordertimeColumn;
    @FXML
    private TableColumn<SimpleKitchenViewModel, String> menuitemnameColumn;
    @FXML
    private TableColumn<SimpleKitchenViewModel, String> statusColumn;
    @FXML
    private Button markAsPreparatoryButton;
    @FXML
    private Button markAsPreparedButton;

    private KitchenStaffViewModel kitchenStaffViewModel;
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.kitchenStaffViewModel = viewModelFactory.getKitchenStaffViewModel();

        orderIDColumn.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        tablenameColumn.setCellValueFactory(cellData -> cellData.getValue().tablenameProperty());
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        //ordertimeColumn.setCellValueFactory(cellData -> cellData.getValue().ordertimeProperty());
        menuitemnameColumn.setCellValueFactory(cellData -> cellData.getValue().menuitemnameProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tableView.setItems(kitchenStaffViewModel.getKitchenList());
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void markAsPreparatory() {
        kitchenStaffViewModel.preparing();
    }

    @FXML
    private void markAsPrepared() {
        kitchenStaffViewModel.prepared();
    }
}