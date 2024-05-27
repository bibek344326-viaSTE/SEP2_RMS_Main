package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddNewMenuItemsController {
    @FXML private Label headerLabel;
    @FXML private TextField itemNameField;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;
    @FXML private Button backButton;

    private ViewHandler viewHandler;
    private AddNewMenuItemsViewModel viewModel;
    private Region root;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.viewModel = viewModelFactory.getAddNewMenuItemsViewModel();

        itemNameField.textProperty().bindBidirectional(viewModel.foodnameProperty());
        typeComboBox.valueProperty().bindBidirectional(viewModel.drinksProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
        headerLabel.textProperty().bind(viewModel.headerProperty());

        typeComboBox.getItems().addAll("Food", "Drink"); // Assuming these are the types

        reset();
    }

    public void reset() {
        try {
            viewModel.reset();
        } catch (Exception e) {
            errorLabel.setText("Error resetting: " + e.getMessage());
        }
    }

    @FXML private void confirmButton() throws SQLException, RemoteException {
        boolean ok = viewModel.updateMenuItem();
        if (ok) {
            viewHandler.openView("menuView");
        }
    }

    @FXML private void createButton() throws SQLException, RemoteException {
        boolean ok = viewModel.createMenuItem();
        if (ok) {
            viewHandler.openView("menuView");
        }
    }

    @FXML private void backButton() throws SQLException, RemoteException {
        viewHandler.openView("menuView");
    }

    public Region getRoot() {
        return root;
    }
}