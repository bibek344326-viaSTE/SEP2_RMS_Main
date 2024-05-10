package server.database.table;

import server.database.DatabaseConnection;
import shared.utils.table.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDAOManager implements TableDAO {

    private ArrayList<Table> getTable(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Table> tables = new ArrayList<>();
        while (resultSet.next()) {
            String tableName = resultSet.getString("table_number");
            int capacity = resultSet.getInt("table_capacity");
            //boolean isOccupied = resultSet.getBoolean("is_occupied");
            Table table = new Table(tableName, capacity);
            tables.add(table);
        }
        return tables;
    }

    @Override
    public void create(Table table) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tables (table_number, table_capacity, is_occupied) VALUES (?,?,?)");

            preparedStatement.setString(1, table.getTableName());
            preparedStatement.setInt(2, table.getTableCapacity());
            preparedStatement.setBoolean(3, table.isOccupied());
            preparedStatement.executeUpdate();

            System.out.println("Table added successfully.");
        }
    }

    @Override
    public void update(Table table, String tableName, int tableCapacity) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE tables SET table_capacity = ? WHERE table_number = ?");
            preparedStatement.setInt(1, tableCapacity);
            preparedStatement.setString(2, tableName);
            preparedStatement.executeUpdate();

            System.out.println("Table updated successfully.");
        }
    }

    @Override
    public void delete(Table table) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM tables WHERE table_number = ?");
            preparedStatement.setString(1, table.getTableName());
            preparedStatement.executeUpdate();

            System.out.println("Table deleted successfully.");
        }
    }

    @Override
    public List<Table> getAllTables() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM tables");
            return getTable(preparedStatement);
        }
    }


    @Override
    public List<Table> getAvailableTables() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM tables WHERE is_occupied = ?");
            preparedStatement.setBoolean(1, false);

            return getTable(preparedStatement);
        }
    }

    @Override
    public List<Table> getAllOccupiedTables() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM tables WHERE is_occupied = ?");
            preparedStatement.setBoolean(1, true);

            return getTable(preparedStatement);
        }
    }
}
