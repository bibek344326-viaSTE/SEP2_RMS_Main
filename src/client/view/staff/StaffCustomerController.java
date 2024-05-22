package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StaffCustomerController implements ViewController {

  ;
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
