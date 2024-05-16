package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class StaffTabViewController implements ViewController {
    @FXML
    private TabPane tabPane;
    @FXML private StaffCustomerViewController staffCustomerViewController;
    @FXML private TableViewController tableViewController;
    @FXML private StaffOrderViewController staffOrderViewController;
    @FXML private StaffMenuViewController staffMenuViewController;
    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;


    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
    }


}
