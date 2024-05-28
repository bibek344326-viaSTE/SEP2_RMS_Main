package server.model.kitchenOrders;

import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public interface KitchenOrdersListHandler {
    ArrayList<KitchenOrder> getKitchenOrders();

    void updateKitchenOrder(int orderId, int menuitemid, String preparationStatus);
}
