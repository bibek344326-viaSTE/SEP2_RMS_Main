package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StaffMenuItemsViewController implements ViewController {
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
    private StaffMenuItemsViewModel menuViewModel;
    private Region root;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException, SQLException {
        this.viewHandler = viewHandler;
        this.menuViewModel = viewModelFactory.getMenuViewModel();
        this.root = root;

        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().getItemNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().getItemIdProperty());

        errorLabel.textProperty().bind(menuViewModel.getErrorProperty());

        menuTableView.setItems(menuViewModel.getMenuItemList());

        menuViewModel.setSelected(null);
        menuViewModel.deselect();

        menuTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                menuViewModel.setSelected(newSelection);
            }
        });
    }

    public void reset() {
        menuViewModel.clear();
        menuTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void deleteMenuItemButton(ActionEvent event) throws SQLException, RemoteException {
        menuViewModel.remove();
    }

    @FXML
    private void clearSelectedMenuItemButton(ActionEvent event) {
        menuTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void addEditButton(ActionEvent event) throws SQLException, RemoteException {
        menuViewModel.addEdit();
        viewHandler.openView("addNewMenuItems");
    }
    @FXML
    private void update() throws SQLException, RemoteException {
        menuViewModel.updateMenuItemList();
    }

    @FXML
    private void back(ActionEvent event) throws SQLException, RemoteException {
        viewHandler.openView("connectionButtons");
    }

    public Region getRoot() {
        return root;
    }
}


