package shared.networking.serverInterfaces;

import shared.networking.clientInterfaces.TablesCallBack;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public interface TableServer extends Remote {
    List<Table> getTables() throws RemoteException;

    Request addTable(String tablename, int tableCapacity) throws RemoteException, SQLException;

    void updateTable(Table table, String tableName, int tableCapacity) throws RemoteException;

    void deleteTable(Table table) throws RemoteException;

    void registerTableClient(TablesCallBack tablesCallBack) throws RemoteException;

    Request reserveTable(Reservation reservation) throws RemoteException, SQLException;

    Request removeReservation(int id) throws RemoteException;

}
