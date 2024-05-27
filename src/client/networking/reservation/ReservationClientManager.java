package client.networking.reservation;

import client.model.Reservation.ReservationList;
import client.networking.GetServer;
import server.database.reservation.ReservationDAO;
import server.database.reservation.ReservationDAOManager;
import shared.networking.serverInterfaces.LoginServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationClientManager implements ReservationClient {
    private ReservationDAO reservationDAO;
    private PropertyChangeSupport support;

    public ReservationClientManager() throws SQLException {
        reservationDAO = new ReservationDAOManager();
        support = new PropertyChangeSupport(this);
    }


    @Override
    public void clearReservation(Reservation reservation) {
        reservationDAO.deleteReservation(reservation.getId());
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationDAO.getAllReservations();
    }


    @Override
    public void getReservationById(int id) {
        support.firePropertyChange("Reservation", null, reservationDAO.getReservation(id));
    }

    @Override
    public void getReservationsByCustomer(Customer customer) {
        support.firePropertyChange("Reservations", null, reservationDAO.getReservationsByUsername(customer.getUsername()));
    }

    @Override
    public void reserveTable(Reservation reservation, Table table, Customer customer) throws SQLException {
        reservationDAO.addReservation(customer.getUsername(), LocalDateTime.now(), table.getTableName());
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

