package server.database.table;

import shared.utils.table.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TableDAO {
    void create(Table table) throws SQLException;

    void update(Table table, String tableName, int tableCapacity) throws SQLException;

    void delete(Table table) throws SQLException;

    List<Table> getAllTables() throws SQLException;

    List<Table> getAvailableTables() throws SQLException;

    List<Table> getAllOccupiedTables() throws SQLException;

    Table getTable(String tableName) throws SQLException;
}
