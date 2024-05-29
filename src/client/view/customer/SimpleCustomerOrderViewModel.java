package client.view.customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.kitchenOrder.KitchenOrder;


public class SimpleCustomerOrderViewModel {
    private final StringProperty foodnameProperty;
    //private final StringProperty foodtype;
    private final StringProperty statusProperty;

    public SimpleCustomerOrderViewModel(KitchenOrder kitchenOrder) {
        this.foodnameProperty = new SimpleStringProperty(kitchenOrder.getMenuItemName());
        //this.foodtype = new SimpleStringProperty(kitchenOrder.);
        this.statusProperty= new SimpleStringProperty(kitchenOrder.getPreparationStatus());
    }
    public StringProperty getFoodnameProperty() {return foodnameProperty;}

    public StringProperty getStatusProperty() {return statusProperty;}

}
