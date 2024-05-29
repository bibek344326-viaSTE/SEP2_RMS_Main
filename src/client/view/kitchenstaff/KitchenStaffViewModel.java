package client.view.kitchenstaff;

import client.core.ModelFactory;
import client.core.ViewState;
import client.model.kitchenOrders.KitchenOrdersModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.utils.kitchenOrder.KitchenOrder;


import java.rmi.RemoteException;

public class KitchenStaffViewModel {
    private KitchenOrdersModel kitchenOrdersModel;
    private ViewState viewState;
    private ObservableList<SimpleKitchenViewModel> kitchenList;
    private ObjectProperty<SimpleKitchenViewModel> selectedKitchenProperty;
    private StringProperty errorLabel;


    public KitchenStaffViewModel(ModelFactory modelFactory, ViewState viewState) throws RemoteException {
        this.viewState = viewState;
        this.kitchenOrdersModel = modelFactory.getKitchenOrdersModel();
        this.kitchenList = FXCollections.observableArrayList();
        this.selectedKitchenProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty();
    }

    public ObservableList<SimpleKitchenViewModel> getKitchenList () {return kitchenList;}

    public void clear(){
        errorLabel.set(null);
    }

    public void preparing() {
        kitchenOrdersModel.updateKitchenOrder(
                selectedKitchenProperty.get().orderIDProperty().get(),
                selectedKitchenProperty.get().menuitemnameProperty().get(),
                "PREPARING");
    }
    public void prepared() {
        kitchenOrdersModel.updateKitchenOrder(
                selectedKitchenProperty.get().orderIDProperty().get(),
                selectedKitchenProperty.get().menuitemnameProperty().get(),
                "PREPARED");
    }


}

