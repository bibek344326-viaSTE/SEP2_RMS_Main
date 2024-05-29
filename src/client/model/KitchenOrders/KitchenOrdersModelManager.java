package client.model.KitchenOrders;

import client.networking.kitchenOrders.KitchenOrdersClient;
import client.networking.kitchenOrders.KitchenOrdersClientManager;
import shared.utils.kitchenOrder.KitchenOrder;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class KitchenOrdersModelManager implements KitchenOrdersModel {
    private KitchenOrdersClient client;

    public KitchenOrdersModelManager(KitchenOrdersClient kitchenOrdersClient) {
        this.client = kitchenOrdersClient;
    }

    @Override
    public ArrayList<KitchenOrder> getKitchenOrders() {
        return client.getKitchenOrders();
    }

    @Override
    public void updateKitchenOrder(int orderId, int menuitemid, String preparationStatus) {
        client.updateKitchenOrder(orderId, menuitemid, preparationStatus);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        addListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        removeListener(eventName, listener);
    }

    public static void main(String[] args) {
        KitchenOrdersClient client = new KitchenOrdersClientManager();
        KitchenOrdersModelManager model = new KitchenOrdersModelManager(client);
        for (int i = 0; i < model.getKitchenOrders().size(); i++) {
            System.out.println(model.getKitchenOrders().get(i).getOrderId() +
                    "\n" + model.getKitchenOrders().get(i).getReservationID() +
                    "\n" + model.getKitchenOrders().get(i).getPreparationStatus() + "\n");
        }

    }
}
