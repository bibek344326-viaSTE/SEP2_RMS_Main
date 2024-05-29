package client.view.customer;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import shared.utils.user.Usertype;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CustomerLoginController implements ViewController {
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;
    private CustomerLoginViewModel customerLoginViewModel;
    private Region root;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button logInButton;
    @FXML
    private Button backButton;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
        this.root = root;
        this.customerLoginViewModel = viewModelFactory.getCustomerLoginViewModel();
        bindFields();
    }

    private void bindFields() {
        usernameField.textProperty().bindBidirectional(customerLoginViewModel.getUsernameLogin());
        passwordField.textProperty().bindBidirectional(customerLoginViewModel.getPasswordLogin());
        errorLabel.textProperty().bind(customerLoginViewModel.getErrorLogin());
    }

    @FXML
    private void signIn() throws SQLException, RemoteException {
        String temp = customerLoginViewModel.login();
        if (temp.equals(Usertype.CUSTOMER.toString())) {
            viewHandler.openView("customerViewOrderStatus");
        } else {
            System.out.println("Wrong username or password");
        }
    }

    @FXML
    private void back() throws SQLException, RemoteException {
        viewHandler.openView("login");
    }


    public Region getRoot() {
        return root;
    }
}