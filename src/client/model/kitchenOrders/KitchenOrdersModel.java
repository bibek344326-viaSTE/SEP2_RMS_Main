package client.model.kitchenOrders;

import shared.utils.Subject;
import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public interface KitchenOrdersModel extends Subject {
    ArrayList<KitchenOrder> getKitchenOrders();
    void updateKitchenOrder(int orderId, String menuitemid, String preparationStatus);
}
