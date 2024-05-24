package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.utils.menuItem.MenuItem;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffMenuViewController implements ViewController {
    @FXML
    private TableView<SimpleMenuViewModel> menuTableView;
    @FXML
    private TableColumn<SimpleMenuViewModel, String> itemNameColumn;
    @FXML
    private TableColumn<SimpleMenuViewModel, String> typeColumn;
    @FXML
    private TableColumn<SimpleMenuViewModel, Number> IdColumn;
    @FXML
    private Button clearSelectedMenuItemButton;
    @FXML
    private Button addNewMenuItemButton;
    @FXML
    private Button editMenuItemDetailsButton;
    @FXML
    private Button deleteMenuItemButton;
    @FXML
    private Label errorLabel;

    private ViewHandler viewHandler;
    private MenuViewModel menuViewModel;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) throws RemoteException, SQLException {
        this.viewHandler = viewHandler;
        this.menuViewModel = viewModelFactory.getMenuViewModel();

        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().getItemNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().getItemIdProperty());

        //errorLabel.textProperty().bind(menuViewModel.getErrorProperty());

        menuTableView.setItems(menuViewModel.getMenuItemList());

        menuViewModel.setSelected(null);
        menuViewModel.deselect();

        menuTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                menuViewModel.setSelected(newSelection);
            }
        });
    }

    @FXML
    private void deleteMenuItemButton() throws SQLException, RemoteException {
        menuViewModel.remove();
    }

    @FXML
    private void clearSelectedMenuItemButton(ActionEvent event) {
        menuTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void addEditButton(ActionEvent event) {
        menuViewModel.addEdit();

    }

    public void back() {
        viewHandler.openConnectionButtons();
    }
}


