package client.model.tables;

import client.networking.tables.TablesClient;
import shared.utils.Observer;
import shared.utils.Request;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableModelManager implements TableModel {


    private TablesClient tablesClient;
    private PropertyChangeSupport support;
    private LocalDateTime localDateTime;
    private Reservation reservation;
    private String tempUserName;

    public TableModelManager(TablesClient tablesClient) {
        this.tablesClient = tablesClient;
        support = new PropertyChangeSupport(this);
        tablesClient.addListener(Observer.AVAILABLETABLES.toString(), this::fireProperty);
    }

    private void fireProperty(PropertyChangeEvent event) {
        support.firePropertyChange(event);
    }

    @Override
    public List<Table> getTables() {
        return tablesClient.getTables();
    }

    @Override
    public void updateTable(Table table, String tableName, int tableCapacity) {
        tablesClient.updateTable(table, tableName, tableCapacity);
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public Request reserveTable(String tableName, List<Table> tables) {
        System.out.println("Table model Manager");
        List<Table> temp = new ArrayList<>();
        for (Table table : tables) {
            temp.add(table);
        }

        try {
            Reservation reservation1 = new Reservation(tempUserName, localDateTime, temp);
            return tablesClient.reserveTable(reservation1);
        } catch (Exception e) {
            return new Request(e.getMessage(), null);
        }
    }

    @Override
    public Reservation getSelectedReservation() {
        return reservation;
    }

    @Override
    public String getUserName() {
        return tempUserName;
    }

    @Override
    public Request createTable(Table table) {
        return tablesClient.addTable(table);
    }

    @Override
    public Request removeReservation(int id) {
        return tablesClient.removeReservation(id);
    }

    @Override
    public void deleteTable(String tableName) throws RemoteException, SQLException {
        tablesClient.deleteTable(tableName);
    }

    @Override
    public List<Table> getAvailableTables() throws RemoteException, SQLException {
        return tablesClient.getAvailableTables();
    }

    @Override
    public List<Table> getOccupiedTables() throws RemoteException, SQLException {
        return tablesClient.getOccupiedTables();
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
