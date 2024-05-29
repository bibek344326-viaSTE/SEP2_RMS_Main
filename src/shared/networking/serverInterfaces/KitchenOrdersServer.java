package shared.networking.serverInterfaces;

import shared.utils.kitchenOrder.KitchenOrder;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface KitchenOrdersServer extends Remote {

    void updateKitchenOrder(int orderId, String menuitemname, String preparationStatus) throws RemoteException;

    ArrayList<KitchenOrder> getKitchenOrders() throws RemoteException;


}
