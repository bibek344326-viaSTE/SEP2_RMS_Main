package client.model.tables;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface TableModel extends Subject {
    List<Table> getTables();
    void updateTable(Table table, String tableName, int tableCapacity);
    LocalDateTime getDateTime();
    Request reserveTable(String tableName, List<Table> tables);
    Reservation getSelectedReservation();
    String getUserName();
    Request createTable(Table table);
    Request removeReservation(int id);
    void deleteTable(String tableName) throws RemoteException, SQLException;

    List<Table> getAvailableTables() throws RemoteException, SQLException;
    List<Table> getOccupiedTables() throws RemoteException, SQLException;

}
