package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StaffMenuViewController {

    /* @FXML
     private TableView<SimpleMenuViewModel> tableView;
     @FXML
     private TableColumn<SimpleMenuViewModel, String> itemNameColumn;
     @FXML
     private TableColumn<SimpleMenuViewModel, String> itemTypeColumn;*/
    @FXML
    private Button addNewItemButton;
    @FXML
    private Button editItemDetailsButton;
    @FXML
    private Button deleteItemButton;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
}



       /* tableView.setItems(staffMenuViewModel.getMenuList());
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().getItemNameProperty());
        itemTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getItemTypeProperty());
        //errorLabel.textProperty().bind(tableViewModel.getErrorProperty());

        staffMenuViewModel.setSelected(null);
        staffMenuViewModel.deselect();

        // Selecting item
        TableView.TableViewSelectionModel<SimpleMenuViewModel> selectionModel = tableView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                staffMenuViewModel.setSelected(selectionModel.getSelectedItem());
            }
        });
    }*/


   /* public void deleteItem(ActionEvent actionEvent) {
        staffMenuViewModel.deleteMenuItems();
    }

    public void addNewItem(ActionEvent actionEvent) {
        staffMenuViewModel.editMenuItems();

    }
    public void editNewItem(ActionEvent actionEvent) {
        staffMenuViewModel.addNewMenuItems();

    }
}*/