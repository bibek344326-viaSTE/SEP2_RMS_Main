package client.view.customer;

import client.core.ViewModelFactory;
import client.networking.login.LoginClientManager;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import server.model.login.LoginHandler;
import shared.networking.serverInterfaces.CreateAccountServer;
import shared.utils.user.Usertype;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CustomerLoginController implements ViewController {
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;
    private CustomerLoginViewModel customerLoginViewModel;

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
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
        this.root = root;
        customerLoginViewModel = viewModelFactory.getCustomerLoginViewModel();


    }

    public void logInAsCustomer() throws SQLException, RemoteException {
        viewHandler.openView("customerViewOrderStatus");
    }

    public void back() throws SQLException, RemoteException {
        viewHandler.openView("login");
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void signIn(ActionEvent event) throws SQLException, RemoteException {
        String temp = customerLoginViewModel.login();
        if (temp.equals(Usertype.CUSTOMER.toString())) {
            viewHandler.openView("customerViewOrderStatus");
        } else {
            System.out.println("Wrong username or password");
        }
    }
}