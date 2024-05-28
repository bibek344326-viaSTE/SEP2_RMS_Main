package client.view.staff.Order;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import shared.utils.order.Order;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffOrderViewController implements ViewController {
    @FXML
    private TableView<SimpleStaffOrderViewModel> tableView;
    @FXML
    private TableColumn<SimpleStaffOrderViewModel, Number> orderIDColumn;
    @FXML
    private TableColumn<SimpleStaffOrderViewModel, String> tablenameColumn;
    @FXML
    private TableColumn<SimpleStaffOrderViewModel, String> customerNameColumn;
    @FXML
    private TableColumn<SimpleStaffOrderViewModel, String> ordertimeColumn;
    @FXML
    private TableColumn<SimpleStaffOrderViewModel, String> menuitemnameColumn;
    @FXML
    private TableColumn<SimpleStaffOrderViewModel, String> statusColumn;
    @FXML
    private Button markAsPreparatoryButton;
    @FXML
    private Button markAsPreparedButton;

    private StaffOrderViewModel staffOrderViewModel;
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.staffOrderViewModel = viewModelFactory.getStaffOrderViewModel();

        orderIDColumn.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        tablenameColumn.setCellValueFactory(cellData -> cellData.getValue().tablenameProperty());
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        ordertimeColumn.setCellValueFactory(cellData -> cellData.getValue().ordertimeProperty());
        menuitemnameColumn.setCellValueFactory(cellData -> cellData.getValue().menuitemnameProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tableView.setItems(staffOrderViewModel.getOrderList());
    }

    public Region getRoot() {
        return root;
    }

@FXML

    private void back() throws SQLException, RemoteException {
        viewHandler.openView("connectionButtons");
}

}