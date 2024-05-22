package client.view.staff;

import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.ViewHandler;

public class UpdateTableViewController implements ViewController {
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

}
