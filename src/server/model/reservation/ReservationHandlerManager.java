package server.model.reservation;

import shared.utils.reservation.Reservation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ReservationHandlerManager implements ReservationHandler {
    private PropertyChangeSupport support;
    private ArrayList<Reservation> reservations;

    public ReservationHandlerManager() {
        support = new PropertyChangeSupport(this);
        reservations = new ArrayList<>();
    }

    @Override
    public void reserve(Reservation reservation) {
        reservations.add(reservation);
        support.firePropertyChange("reservationAdded", null, reservation);

    }

    @Override
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public Reservation getReservation(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public void clearReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            support.firePropertyChange("reservationRemoved", reservation, null);
        }
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



