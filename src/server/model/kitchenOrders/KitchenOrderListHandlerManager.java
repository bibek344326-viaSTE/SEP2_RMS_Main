package server.model.kitchenOrders;

import server.database.kitchenOrders.KitchenOrdersDAO;
import server.database.kitchenOrders.KitchenOrdersDAOManager;
import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public class KitchenOrderListHandlerManager implements KitchenOrdersListHandler{

    private KitchenOrdersDAO kitchenOrderDAO;

    public KitchenOrderListHandlerManager() {
        kitchenOrderDAO = new KitchenOrdersDAOManager();
    }



    @Override
    public ArrayList<KitchenOrder> getKitchenOrders() {
        return kitchenOrderDAO.getKitchenOrders();
    }

    @Override
    public void updateKitchenOrder(int orderId, String menuitemid, String preparationStatus) {
        kitchenOrderDAO.updateKitchenOrder(orderId, menuitemid, preparationStatus);
    }
}
