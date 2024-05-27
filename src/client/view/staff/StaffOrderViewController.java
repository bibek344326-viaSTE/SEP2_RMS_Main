package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

public class StaffOrderViewController implements ViewController {
    private ViewHandler viewHandler;
    private StaffOrderViewModel staffOrderViewModel;
    private Region root;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
        this.root = root;
    }
    public void back(){
        viewHandler.openView("connectionButtons");
    }
    public Region getRoot()
    {
        return root;
    }

}
