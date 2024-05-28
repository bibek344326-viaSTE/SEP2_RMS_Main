package server.database.kitchenOrders;

import server.database.DatabaseConnection;
import shared.utils.kitchenOrder.KitchenOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class KitchenOrdersDAOManager implements KitchenOrdersDAO {
    public KitchenOrdersDAOManager() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<KitchenOrder> getKitchenOrders() {
        ArrayList<KitchenOrder> kitchenOrders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("\"SELECT t.table_name, r.customer_name, o.order_id, r.reservation_id, m.menu_item_name, o.order_status \" +\n" +
                            "                       \"FROM tables t \" +\n" +
                            "                       \"JOIN reservations r ON t.id = r.table_id \" +\n" +
                            "                       \"JOIN orders o ON r.id = o.reservation_id \" +\n" +
                            "                       \"JOIN menu_items m ON o.menu_item_id = m.id\";");
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kitchenOrders.add(new KitchenOrder(
                        resultSet.getString("table_name"),
                        resultSet.getString("customer_id"),
                        resultSet.getInt("orderid"),
                        resultSet.getInt("reservation_id"),
                        resultSet.getString("menuitemname"),
                        resultSet.getString("orderstatus")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kitchenOrders;
    }

    @Override
    public void updateKitchenOrder(int menuitemid, int orderid, String preparationStatus) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE orderitems SET orderstatus = ? WHERE orderid = ? and menuitemid = ?;");
            preparedStatement.setString(1, preparationStatus);
            preparedStatement.setInt(2, orderid);
            preparedStatement.setInt(3, menuitemid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
