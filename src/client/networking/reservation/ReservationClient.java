package client.networking.reservation;

import client.model.Reservation.ReservationList;
import shared.utils.Subject;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

public interface ReservationClient extends Subject {
    void reserveTable(Reservation reservation, Table table, Customer customer);
    void clearReservation(Reservation reservation);
}
