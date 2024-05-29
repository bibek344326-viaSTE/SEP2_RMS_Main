package client.view.customer;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.kitchenOrders.KitchenOrdersModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.kitchenOrder.KitchenOrder;

import java.rmi.RemoteException;

public class CustomerViewOrderStatusViewModel {
    private final KitchenOrdersModel kitchenOrdersModel;
    private final ViewState viewState;
    private final ObservableList<SimpleCustomerOrderViewModel> list;
    private final ObjectProperty<SimpleCustomerOrderViewModel> selectedItemProperty;

    public CustomerViewOrderStatusViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.kitchenOrdersModel = modelFactory.getKitchenOrdersModel();
        this.viewState = viewState;
        this.list = FXCollections.observableArrayList();
        this.selectedItemProperty = new SimpleObjectProperty<>();
        updateList();
    }

    public ObservableList<SimpleCustomerOrderViewModel> getList() {
        return list;
    }

    public void updateList() {
        list.clear();
        for (KitchenOrder kitchenOrder : kitchenOrdersModel.getKitchenOrders()) {
            list.add(new SimpleCustomerOrderViewModel(kitchenOrder));
        }
    }

    public void setSelected(SimpleCustomerOrderViewModel kitchenOrder) {
        if (kitchenOrder != null) {
            this.selectedItemProperty.set(kitchenOrder);
            viewState.setMenuItemName(kitchenOrder.getFoodnameProperty().get());
            viewState.setStatus2(kitchenOrder.getStatusProperty().get());
        } else {
            this.selectedItemProperty.set(null);
            viewState.setMenuItemName(null);
            viewState.setStatus2(null);
        }
        updateList();
    }

    public void deselect() {
        setSelected(null);
    }
}
