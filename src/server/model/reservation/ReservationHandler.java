package server.model.reservation;

import shared.utils.Subject;
import shared.utils.reservation.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ReservationHandler extends Subject {
    public void reserve(Reservation reservation);
    ArrayList<Reservation> getReservations();
    Reservation getReservation(int id);
    void clearReservation(Reservation reservation);
}
