package client.view.kitchenstaff;

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

public class KitchenStaffViewModel {
    private OrderModel orderModel;
    private ViewState viewState;
    private ObservableList<SimpleKitchenViewModel> kitchenList;
    private ObjectProperty<SimpleKitchenViewModel> selectedKitchenProperty;
    private StringProperty errorLabel;


    public KitchenStaffViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.viewState = viewState;
        this.orderModel = modelFactory.getOrderModel();
        this.kitchenList = FXCollections.observableArrayList();
        this.selectedKitchenProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
        updateKitchenList();
    }

    public ObservableList<SimpleKitchenViewModel> getKitchenList () {return kitchenList;}

    public void clear(){
        errorLabel.set(null);
    }
    public void updateKitchenList() throws RemoteException {
        kitchenList.clear();
        for (Order order : orderModel.getOrders()) {
            kitchenList.add(new SimpleKitchenViewModel(order));
        }
    }
}
