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
        String sql = "INSERT INTO menu_items (name, type) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
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
        String sql = "DELETE FROM menu_items WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuItemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMenuItem(MenuItem menuItem, String newName, String newType, int menuItemID) {
        String sql = "UPDATE menu_items SET name = ?, type = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newType);
            pstmt.setInt(3, menuItemID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem getMenuItem(String menuItemName) {
        String sql = "SELECT * FROM menu_items WHERE name = ?";
        MenuItem menuItem = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuItemName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                menuItem = new MenuItem(name, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItem;
    }

    @Override
    public ArrayList<MenuItem> getMenuItems() {
        String sql = "SELECT * FROM menu_items";
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                MenuItem menuItem = new MenuItem(name, type);
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

}
