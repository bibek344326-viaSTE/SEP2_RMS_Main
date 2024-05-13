package client.view.kitchenstaff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;

public class KitchenStaffViewController implements ViewController {
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
    }

    public void LogInAsKitchenStaff() {
        viewHandler.openKitchenView();
    }
}
