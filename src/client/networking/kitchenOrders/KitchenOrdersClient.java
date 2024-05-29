package client.networking.kitchenOrders;

import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public interface KitchenOrdersClient {
    ArrayList<KitchenOrder> getKitchenOrders();
    void updateKitchenOrder(int orderId, int menuitemid, String preparationStatus);
}
