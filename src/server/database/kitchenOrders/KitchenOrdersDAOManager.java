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
                    ("SELECT r.table_name, r.customer_id, o.orderID, r.reservation_id, mi.menuItemName, oi.orderStatus FROM reservation r JOIN orders o ON r.reservation_id = o.reservation_id \n" +
                            "JOIN orderitems oi ON o.orderId = oi.orderId JOIN  menuitem mi ON oi.menuItemId = mi.menuItem_id;"
                    );
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
                System.out.println(resultSet.getString("table_name") + " " + resultSet.getString("customer_id") + " " + resultSet.getInt("orderid") + " " + resultSet.getInt("reservation_id") + " " + resultSet.getString("menuitemname") + " " + resultSet.getString("orderstatus") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kitchenOrders;
    }

    @Override
    public void updateKitchenOrder(int orderid, String menuitemname, String preparationStatus) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE orderitems SET orderstatus = ? WHERE orderid = ? AND menuitemname = (SELECT menuitem_id FROM menuitem WHERE menuItemName = ?);");
            preparedStatement.setString(1, preparationStatus);
            preparedStatement.setInt(2, orderid);
            preparedStatement.setString(3, menuitemname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
