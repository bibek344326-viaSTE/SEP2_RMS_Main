package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ConnectionButtonViewController implements ViewController {
    @FXML
    private TabPane tabPane;
    @FXML private StaffMemberLoginController staffCustomerViewController;
    @FXML private StaffTableViewController tableViewController;
    @FXML private StaffOrderViewController staffOrderViewController;
    @FXML private StaffMenuItemsViewController staffMenuViewController;
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;
    private Region root;


    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler,Region root) {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
        this.root = root;

    }
    public void openCustomer() throws SQLException, RemoteException {
        viewHandler.openView("customerViewStaff");
    }
    public void openTable() throws SQLException, RemoteException {
        viewHandler.openView("staffTable");
    }
    public void openOrder() throws SQLException, RemoteException {
        viewHandler.openView("orderView");
    }
    public void openMenu() throws SQLException, RemoteException {
        viewHandler.openView("menuView");
    }
    public Region getRoot()
    {
        return root;
    }
}
