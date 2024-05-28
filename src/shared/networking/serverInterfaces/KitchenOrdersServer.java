package shared.networking.serverInterfaces;

import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public interface KitchenOrdersServer {
    void addKitchenOrder(String tableName, String customerName, int orderId, int reservationID, String menuItemName, String preparationStatus);

    void updateKitchenOrder(int orderId, String preparationStatus);

    void removeKitchenOrder(int orderId);

    ArrayList<KitchenOrder> getKitchenOrders();

    void addKitchenOrder(KitchenOrder kitchenOrder);
}
