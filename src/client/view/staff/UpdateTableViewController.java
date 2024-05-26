package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import client.core.IntStringConverter;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class UpdateTableViewController implements ViewController {
    @FXML private Label headerLabel;
    @FXML private TextField tableNameField;
    @FXML private TextField capacityField;
    @FXML private RadioButton statusRadioButton;
    @FXML private Label errorLabel;
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Button createButton;

    private ViewHandler viewHandler;
    private UpdateTableViewModel viewModel;
    private Region root;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler,Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.viewModel = viewModelFactory.getUpdateTableViewModel();
        this.root = root;

        tableNameField.textProperty().bindBidirectional(viewModel.tableNameProperty());
        Bindings.bindBidirectional(capacityField.textProperty(), viewModel.capacityProperty(), new IntStringConverter());
        //statusRadioButton.selectedProperty().bindBidirectional(viewModel.statusProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
        //headerLabel.textProperty().bind(viewModel.headerProperty());

        reset();
    }

    public void reset() {
        viewModel.reset();
    }

    @FXML private void confirmButton() {
        boolean ok = viewModel.updateTable();
        if (ok) {
            viewHandler.openView("staffTable");
        }
    }

    @FXML private void createButton() {
        boolean ok = viewModel.createTable();
        if (ok) {
            viewHandler.openView("staffTable");
        }
    }

    @FXML private void backButton() {
        viewHandler.openView("staffTable");
    }
    public Region getRoot()
    {
        return root;
    }
}