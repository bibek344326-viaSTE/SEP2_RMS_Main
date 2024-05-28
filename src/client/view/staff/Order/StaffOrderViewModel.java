package client.view.staff.Order;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.order.OrderModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.order.Order;

import java.rmi.RemoteException;

public class StaffOrderViewModel {
    private OrderModel orderModel;
    private ViewState viewState;
    private ObservableList<SimpleStaffOrderViewModel> orderList;
    private ObjectProperty<SimpleStaffOrderViewModel> selectedOrderProperty;
    private StringProperty errorLabel;

    public StaffOrderViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.viewState = viewState;
        this.orderModel = modelFactory.getOrderModel();
        this.orderList = FXCollections.observableArrayList();
        this.selectedOrderProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        updateOrderList();
    }

    public ObservableList<SimpleStaffOrderViewModel> getOrderList() {
        return orderList;
    }

    public void clear() {
        errorLabel.set(null);
    }

    public void updateOrderList() throws RemoteException {
        orderList.clear();
        for (Order order : orderModel.getOrders()) {
            orderList.add(new SimpleStaffOrderViewModel(order));
        }
    }


    }
