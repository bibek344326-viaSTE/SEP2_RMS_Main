package server.model.tables;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.sql.SQLException;
import java.util.List;

public interface TablesHandler extends Subject {
    List<Table> getTables();

    void updateTable(Table table, String tableName, int tableCapacity);

    Request reserveTable(Reservation reservation) throws SQLException;

    Request removeTable(String table) throws SQLException;

    Request removeReservation(int id);

    Request addTable(Table table) throws SQLException;

    List<Table> getAvailableTables() throws SQLException;
    List<Table> getOccupiedTables() throws SQLException;

}
