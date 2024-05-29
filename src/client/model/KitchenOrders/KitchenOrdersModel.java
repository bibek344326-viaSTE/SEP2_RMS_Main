package client.model.KitchenOrders;

import shared.utils.Subject;
import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public interface KitchenOrdersModel extends Subject {
    ArrayList<KitchenOrder> getKitchenOrders();
    void updateKitchenOrder(int orderId, int menuitemid, String preparationStatus);
}
