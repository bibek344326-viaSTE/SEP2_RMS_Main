package client.networking.tables;

import client.networking.GetServer;
import shared.networking.clientInterfaces.TablesCallBack;
import shared.networking.serverInterfaces.Server;
import shared.utils.Observer;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class TablesClientManager implements TablesClient, TablesCallBack {
    private Server server;
    private PropertyChangeSupport support;

    public TablesClientManager() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            support = new PropertyChangeSupport(this);
            server = GetServer.getServerFromRmi();
            server.getTableServer().registerTableClient(this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Table> getTables() {
        try {
            return server.getTableServer().getTables();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Request reserveTable(Reservation reservation) {
        try {
            return server.getTableServer().reserveTable(reservation);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Request removeReservation(int reservationId) {
        try {
            return server.getTableServer().removeReservation(reservationId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Request addTable(Table table) {
        try {
            return server.getTableServer().addTable(table.getTableName(), table.getTableCapacity());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTable(Table table, String tableName, int tableCapacity) {
        try {
            server.getTableServer().updateTable(table, tableName, tableCapacity);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTable(String tableName) throws RemoteException, SQLException {
        server.getTableServer().deleteTable(tableName);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);

    }


    @Override
    public void tablesCallBack(List<Table> tableList) throws RemoteException {
        System.out.println(tableList);
        support.firePropertyChange(Observer.AVAILABLETABLES.name(), null, tableList);
    }
}
