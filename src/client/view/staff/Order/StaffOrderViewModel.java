package client.view.staff.Order;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.order.OrderModel;

import java.rmi.RemoteException;

public class StaffOrderViewModel {
    private OrderModel orderModel;
    private ViewState viewState;

    public StaffOrderViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.orderModel = modelFactory.getOrderModel();
        this.viewState = viewState;
    }
}
