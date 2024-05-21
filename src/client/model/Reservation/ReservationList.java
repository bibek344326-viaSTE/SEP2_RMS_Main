package client.model.Reservation;

import shared.utils.reservation.Reservation;
import shared.utils.table.Table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ReservationList implements PropertyChangeListener {
    private List<Reservation> reservations;

    public ReservationList() {
        reservations = new ArrayList<>();
    }

    // Method to add a reservation
    public void addReservation(Reservation reservation) {reservations.add(reservation);}
    // Method to remove a reservation
    public void removeReservation(Reservation reservation) {reservations.remove(reservation);}
    // Method to find a reservation by table number
    public Reservation findReservationByTable(String tableName) {
        for (Reservation reservation : reservations) {
            if (reservation.getTable().getTableName().equals(tableName)) {
                return reservation;
            }
        }
        return null;
    }
    // Method to get the number of reservations
    public int getNumberOfReservations() {return reservations.size();}
    // Method to get all reservations
    public List<Reservation> getAllReservations() {return reservations;}

    @Override
    public String toString() {
        return "ReservationList{" +
                "reservations=" + reservations +
                '}';
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("reservationStatus")) {
            boolean newOccupied = (boolean) evt.getNewValue();
            String tableName = ((Table) evt.getSource()).getTableName();

            if (newOccupied) {
                System.out.println("Table " + tableName + " has been reserved.");
            } else {
                System.out.println("Reservation for Table " + tableName + " has been cleared.");
            }
        }
    }
}
