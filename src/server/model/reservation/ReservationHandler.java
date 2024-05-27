package server.model.reservation;

import shared.utils.Subject;
import shared.utils.reservation.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ReservationHandler extends Subject {
    public void reserve(Reservation reservation) throws SQLException;
    ArrayList<Reservation> getReservations();
    Reservation getReservation(int id);
    void clearReservation(Reservation reservation);
    ArrayList<Reservation> getReservationsByUsername(String username);
}
