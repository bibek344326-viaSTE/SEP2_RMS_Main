package client.networking.kitchenOrders;

import client.networking.GetServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.kitchenOrder.KitchenOrder;

import java.util.ArrayList;

public class KitchenOrdersClientManager implements KitchenOrdersClient {
    private Server server;

    public KitchenOrdersClientManager() {
        try {
            this.server = GetServer.getServerFromRmi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<KitchenOrder> getKitchenOrders() {
        try {
            return server.getKitchenOrdersServer().getKitchenOrders();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void updateKitchenOrder(int orderId, String menuitemid, String preparationStatus) {
        try {
            server.getKitchenOrdersServer().updateKitchenOrder(orderId, menuitemid, preparationStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
