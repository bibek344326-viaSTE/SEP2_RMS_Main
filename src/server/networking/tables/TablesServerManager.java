package server.networking.tables;

import server.model.tables.TablesHandler;
import shared.networking.clientInterfaces.TablesCallBack;
import shared.networking.serverInterfaces.TableServer;
import shared.utils.Observer;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesServerManager implements TableServer {
    private TablesHandler tablesHandler;
    private TablesCallBack tablesCallBack;

    public TablesServerManager(TablesHandler tablesHandler) throws RemoteException {
        this.tablesHandler = tablesHandler;
        UnicastRemoteObject.exportObject(this, 0);
        tablesHandler.addListener(Observer.AVAILABLETABLES.toString(), this::fireAvailableTables);
    }

    @Override
    public List<Table> getTables() throws RemoteException {
        return tablesHandler.getTables();
    }

    @Override
    public Request addTable(String tablename, int tableCapacity) throws RemoteException, SQLException {
        return tablesHandler.addTable(new Table(tablename, tableCapacity));
    }

    @Override
    public void updateTable(Table table, String tableName, int tableCapacity) throws RemoteException {
        tablesHandler.updateTable(table, tableName, tableCapacity);
    }

    @Override
    public void deleteTable(String tableName) throws RemoteException, SQLException {
        tablesHandler.removeTable(tableName);
    }

    @Override
    public void registerTableClient(TablesCallBack tablesCallBack) throws RemoteException {
        this.tablesCallBack = tablesCallBack;
    }

    @Override
    public Request reserveTable(Reservation reservation) throws RemoteException, SQLException {
        return tablesHandler.reserveTable(reservation);
    }

    @Override
    public Request removeReservation(int id) throws RemoteException {
        return tablesHandler.removeReservation(id);
    }

    private void fireAvailableTables(PropertyChangeEvent event) {
        try {
            //  System.out.println("\n\n\n "+event.getNewValue());
            tablesCallBack.tablesCallBack((List<Table>) event.getNewValue());
        } catch (RemoteException e) {
            throw new RuntimeException("Error in server");
        }

    }
}
