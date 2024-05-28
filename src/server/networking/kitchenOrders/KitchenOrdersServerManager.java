package server.networking.kitchenOrders;

import server.model.kitchenOrders.KitchenOrdersListHandler;
import shared.networking.serverInterfaces.KitchenOrdersServer;
import shared.utils.kitchenOrder.KitchenOrder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class KitchenOrdersServerManager implements KitchenOrdersServer {
    KitchenOrdersListHandler kitchenOrdersListHandler;

    public KitchenOrdersServerManager(KitchenOrdersListHandler kitchenOrdersListHandler) throws RemoteException {
        this.kitchenOrdersListHandler = kitchenOrdersListHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void updateKitchenOrder(int orderId, int menuitemid, String preparationStatus) {
        kitchenOrdersListHandler.updateKitchenOrder(orderId, menuitemid, preparationStatus);
    }

    @Override
    public ArrayList<KitchenOrder> getKitchenOrders() {
        return kitchenOrdersListHandler.getKitchenOrders();
    }

}
