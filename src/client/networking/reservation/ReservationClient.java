package client.networking.reservation;

import client.model.Reservation.ReservationList;
import client.model.Reservation.ReservationModel;
import shared.utils.Subject;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ReservationClient extends Subject {
    void reserveTable(Reservation reservation, Table table, Customer customer) throws SQLException;
    void clearReservation(Reservation reservation);
    List<Reservation> getReservations();
    void getReservationById(int id);
    void getReservationsByCustomer(Customer customer);

}
