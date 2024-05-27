package client.model.Reservation;

import client.networking.reservation.ReservationClient;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

public class ReservationModelManager implements ReservationModel {
    private ReservationClient reservationClient;
    private final ReservationList reservationList;
    private final PropertyChangeSupport property;

    private ReservationModelManager reservationModelManager;

    public ReservationModelManager(ReservationClient reservationClient) {
        property = new PropertyChangeSupport(this);
        this.reservationClient = reservationClient;
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
    public void addReservation(Reservation reservation, Customer customer, Table table) throws SQLException {
        reservationClient.reserveTable(reservation, table, customer);
        reservationList.addReservation(reservation);
        property.firePropertyChange("ReservationAdded", null, reservation);

    }

    @Override
    public void removeReservation(Reservation reservation) {
        reservationClient.clearReservation(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationClient.getReservations();
    }

    @Override
    public Reservation findReservationByTable(String tableName) {
        return reservationList.findReservationByTable(tableName);
    }

    @Override
    public List<Reservation> getReservationsByCustomer(Customer customer) {
        return List.of();
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
