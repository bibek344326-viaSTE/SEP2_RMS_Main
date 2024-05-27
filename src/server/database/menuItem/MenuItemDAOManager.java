package server.database.menuItem;

import server.database.DatabaseConnection;
import shared.utils.menuItem.MenuItem;

import java.sql.*;
import java.util.ArrayList;

public class MenuItemDAOManager implements MenuItemDAO {

    public MenuItemDAOManager() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO menuitem (menuitemname, menuitemtype) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuItem.getMenuItemName());
            pstmt.setString(2, menuItem.getMenuItemType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenuItem(int menuItemId) {
        String sql = "DELETE FROM menuitem WHERE menuitem_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuItemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem updateMenuItem(MenuItem menuItem, String newName, String newType, int menuItemID) {
        String sql = "UPDATE menuitem SET menuitemname = ?, menutiemtype = ? WHERE menuitem_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newType);
            pstmt.setInt(3, menuItemID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItem;
    }

    @Override
    public MenuItem getMenuItem(String menuItemName) {
        String sql = "SELECT * FROM menuitem WHERE menuitemname = ?";
        MenuItem menuItem = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuItemName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("menuitem_id");
                String name = rs.getString("menuitemname");
                String type = rs.getString("menuitemtype");
                menuItem = new MenuItem(name, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItem;
    }

    @Override
    public ArrayList<MenuItem> getMenuItems() throws SQLException {

        ArrayList<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM menuitem");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("menuitem_id");
                String name = rs.getString("menuitemname");
                String type = rs.getString("menuitemtype");
                MenuItem menuItem = new MenuItem(name, type);
                menuItem.setMenuItemID(id);
                menuItems.add(menuItem);
            }
            return menuItems;
        }
    }
}
