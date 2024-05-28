package server.database.reservation;

import shared.utils.Request;
import shared.utils.reservation.Reservation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReservationDAO {
    Request addReservation(String username, LocalDateTime localDateTime, String tableName) throws SQLException;

    Request deleteReservation(int id);

    ArrayList<Reservation> getAllReservations();

    Request updateReservation(int id, String username, LocalDateTime localDateTime, String tableName);

    Request getReservation(String username);

    ArrayList<Reservation> getReservationsByUsername(String username);

    Reservation getReservation(int reservationid);

    void removeReservation(int reservationid);
}
