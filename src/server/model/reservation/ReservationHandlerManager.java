package server.model.reservation;

import server.database.reservation.ReservationDAO;
import server.database.reservation.ReservationDAOManager;
import shared.utils.reservation.Reservation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationHandlerManager implements ReservationHandler {
    private ReservationDAO reservationDAO;
    private PropertyChangeSupport support;


    public ReservationHandlerManager() throws SQLException {
        reservationDAO = new ReservationDAOManager();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void reserve(Reservation reservation) throws SQLException {
        reservationDAO.addReservation(reservation.getUserName(), LocalDateTime.now(), reservation.getTempTable().getTableName());
        support.firePropertyChange("reservationAdded", null, reservation);

    }

    @Override
    public ArrayList<Reservation> getReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public Reservation getReservation(int id) {
        for (Reservation reservation : reservationDAO.getAllReservations()) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public void clearReservation(Reservation reservation) {
        reservationDAO.removeReservation(reservation.getId());
            support.firePropertyChange("reservationRemoved", reservation, null);
    }

    @Override
    public ArrayList<Reservation> getReservationsByUsername(String username) {
        return reservationDAO.getReservationsByUsername(username);
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



