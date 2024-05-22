package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StaffMenuViewController implements ViewController {

    @FXML
    private Button addNewItemButton;
    @FXML
    private Button editItemDetailsButton;
    @FXML
    private Button deleteItemButton;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
    public void back(){
        viewHandler.openConnectionButtons();
    }
}


