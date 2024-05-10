package server.model.tables;

import server.database.reservation.ReservationDAO;
import server.database.reservation.ReservationDAOManager;
import server.database.table.TableDAO;
import server.database.table.TableDAOManager;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TablesHandlerManager implements TablesHandler {
    private TableDAO tablesDAO;
    private PropertyChangeSupport support;
    private ReservationDAO reservationDAO;

    public TablesHandlerManager() {
        tablesDAO = new TableDAOManager();
        reservationDAO = ReservationDAOManager.getInstance();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public List<Table> getTables() {
        List<Table> tablesList = new ArrayList<>();
        try {
            tablesList = tablesDAO.getAllTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablesList;
    }

    @Override
    public void updateTable(Table table, String tableName, int tableCapacity) {
        try {
            tablesDAO.update(table, tableName, tableCapacity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Request reserveTable(Reservation reservation) throws SQLException {
        String username = reservation.getUserName();
        List<String> occupiedTables = reservation.getOccupiedTables();
        LocalDateTime localDateTime = reservation.getDateTime();
        Request temp = new Request("Error", null);
        for (String occupiedTable : occupiedTables) {
            temp = reservationDAO.addReservation(username, localDateTime, reservation.getTempTable());
        }
        return temp;
    }

    @Override
    public Request removeTable(Table table) {
        return null;
    }

    @Override
    public Request removeReservation(int id) {
        return reservationDAO.deleteReservation(id);
    }

    @Override
    public Request addTable(Table table) throws SQLException {
        tablesDAO.create(table);
        return new Request("Room created", null);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
