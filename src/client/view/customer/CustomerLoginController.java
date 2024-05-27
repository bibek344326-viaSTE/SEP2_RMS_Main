package client.view.customer;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CustomerLoginController implements ViewController {
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;
    private Region root;

   /* public void clearTableForCustomer() {
        Customer selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerViewModel.clearTableForCustomer(selectedCustomer);
        } else {
            System.out.println("Please select a customer.");
        }
    }*/


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler,Region root) {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
        this.root = root;
    }
    public void logInAsCustomer() throws SQLException, RemoteException {
        viewHandler.openView("customerTableNumber");
    }

    public void back() throws SQLException, RemoteException {
        viewHandler.openView("login");
    }
    public Region getRoot()
    {
        return root;
    }
}