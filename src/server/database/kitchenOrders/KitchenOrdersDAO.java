package server.database.kitchenOrders;

import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public interface KitchenOrdersDAO {
    ArrayList<KitchenOrder> getKitchenOrders();

    void updateKitchenOrder(int menuitemid, String orderid, String preparationStatus);
}
