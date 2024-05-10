package client.networking.tables;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.util.List;

public interface TablesClient extends Subject {
    List<Table> getTables();
    Request reserveTable(Reservation reservation);
    Request removeReservation(int reservationId);
    Request addTable(Table table);
    void updateTable(Table table, String tableName, int tableCapacity);

}
