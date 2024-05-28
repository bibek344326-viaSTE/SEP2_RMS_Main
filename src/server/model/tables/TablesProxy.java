package server.model.tables;

import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public class TablesProxy implements TablesHandler {
    private TablesHandler tablesHandler;
    private String tableName;
    private int tableCapacity;

    public TablesProxy() throws SQLException {
        tablesHandler = new TablesHandlerManager();
    }

    @Override
    public List<Table> getTables() {
        return tablesHandler.getTables();
    }

    @Override
    public void updateTable(Table table, String tableName, int tableCapacity) {
        tablesHandler.updateTable(table, tableName, tableCapacity);
    }

    @Override
    public Request reserveTable(Reservation reservation) throws SQLException {
        System.out.println("Reservation has been done.");
        return tablesHandler.reserveTable(reservation);
    }

    @Override
    public Request removeTable(String table) throws SQLException {
        return tablesHandler.removeTable(table);
    }

    @Override
    public Request removeReservation(int id) {
        return tablesHandler.removeReservation(id);
    }

    @Override
    public Request addTable(Table table) throws SQLException {
        return tablesHandler.addTable(table);
    }

    @Override
    public List<Table> getAvailableTables() throws SQLException {
        return tablesHandler.getAvailableTables();
    }

    @Override
    public List<Table> getOccupiedTables() throws SQLException {
        return tablesHandler.getOccupiedTables();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        tablesHandler.addListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        tablesHandler.removeListener(eventName, listener);
    }
}
