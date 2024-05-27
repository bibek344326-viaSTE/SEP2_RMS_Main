package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CreateNewCustomerController {
    @FXML private Label headerLabel;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button confirmButton;
    @FXML private Button backButton;

    private ViewHandler viewHandler;
    private Region root;
    private CreateNewCustomerViewModel createNewCustomerViewModel;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.createNewCustomerViewModel = viewModelFactory.getCreateNewCustomerViewModel();

        // Bind view model properties to UI elements
        usernameField.textProperty().bindBidirectional(createNewCustomerViewModel.usernameProperty());
        passwordField.textProperty().bindBidirectional(createNewCustomerViewModel.passwordProperty());
        headerLabel.textProperty().bind(createNewCustomerViewModel.headerProperty());
    }
public void reset() throws SQLException, RemoteException {
        createNewCustomerViewModel.reset();
}
    @FXML
    private void confirmButtonClicked() throws SQLException, RemoteException {
        boolean ok = createNewCustomerViewModel.createCustomer();
        if (ok) {
            viewHandler.openView("createNewCustomer");
        }
    }

    @FXML
    private void backButtonClicked() throws SQLException, RemoteException {
        viewHandler.openView("createNewCustomer");
    }

    public Region getRoot() {
        return root;
    }
}
