package client.model.Reservation;

import client.networking.reservation.ReservationClient;
import shared.utils.reservation.Reservation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ReservationModelManager implements ReservationModel{
    private final ReservationList reservationList;
    private final PropertyChangeSupport property;

    private ReservationModelManager reservationModelManager;

    public ReservationModelManager(ReservationClient reservationClient) {
        property = new PropertyChangeSupport(this);
        reservationList = new ReservationList();
        createDummyData();
    }

    private void createDummyData() {
        // Create dummy reservations
        // Example:
        // Reservation reservation1 = new Reservation(LocalDateTime.of(2024, 4, 24, 10, 0), someTable);
        // reservationList.addReservation(reservation1);
        // You can add more dummy data as needed
    }

    @Override
    public void addReservation(Reservation reservation) {
       reservationList.addReservation(reservation);
        property.firePropertyChange("ReservationAdded", null, reservation);
    }

    @Override
    public void removeReservation(Reservation reservation) {
        reservationList.removeReservation(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationList.getAllReservations();
    }

    @Override
    public Reservation findReservationByTable(String tableName) {
        return reservationList.findReservationByTable(tableName);
    }

    @Override
    public int getNumberOfReservations() {
        return reservationList.getNumberOfReservations();
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        addListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        removeListener(listener);
    }

    @Override
    public void loginAsCustomer(String hi) {
        System.out.println("Welcome");
    }


}
