package client.networking.tables;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface TablesClient extends Subject {
    List<Table> getTables();
    Request reserveTable(Reservation reservation);
    Request removeReservation(int reservationId);
    Request addTable(Table table);
    void updateTable(Table table, String tableName, int tableCapacity);
    void deleteTable(String tableName) throws RemoteException, SQLException;
    List<Table> getAvailableTables() throws RemoteException, SQLException;
    List<Table> getOccupiedTables() throws RemoteException, SQLException;

}
