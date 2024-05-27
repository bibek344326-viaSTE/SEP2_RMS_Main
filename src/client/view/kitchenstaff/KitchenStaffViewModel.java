package client.view.kitchenstaff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.order.OrderModel;

import java.rmi.RemoteException;

public class KitchenStaffViewModel {
    private OrderModel orderModel;
    private ViewState viewState;


    public KitchenStaffViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.viewState = viewState;
        this.orderModel = modelFactory.getOrderModel();
    }
}
