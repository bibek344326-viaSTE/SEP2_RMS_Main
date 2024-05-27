package client.model.Reservation;

import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.List;

public interface ReservationModel {
    void addReservation(Reservation reservation);

    void addReservation(Reservation reservation, Customer customer, Table table) throws SQLException;

    void removeReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    Reservation findReservationByTable(String tableName);
    List<Reservation> getReservationsByCustomer(Customer customer);

    int getNumberOfReservations();

    void addListener(PropertyChangeListener listener);

    void removeListener(PropertyChangeListener listener);
    void loginAsCustomer(String hi);

}
