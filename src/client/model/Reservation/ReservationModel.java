package client.model.Reservation;

import shared.utils.reservation.Reservation;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface ReservationModel {
    void addReservation(Reservation reservation);

    void removeReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    Reservation findReservationByTable(String tableName);

    int getNumberOfReservations();

    void addListener(PropertyChangeListener listener);

    void removeListener(PropertyChangeListener listener);
    void loginAsCustomer(String hi);

}
