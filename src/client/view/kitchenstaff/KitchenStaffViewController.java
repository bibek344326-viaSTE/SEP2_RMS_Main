package client.view.kitchenstaff;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class KitchenStaffViewController {
    private KitchenStaffViewModel kitchenStaffViewModel;
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.kitchenStaffViewModel = viewModelFactory.getKitchenStaffViewModel();
    }
    public Region getRoot()
    {
        return root;
    }
}
