package server.database.reservation;

import shared.utils.Request;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ReservationDAO {
    Request addReservation(String username, LocalDateTime localDateTime, String tableName) throws SQLException;

    Request deleteReservation(int id);

    Request updateReservation(int id, String username, LocalDateTime localDateTime, String tableName);

    Request getReservation(String username);

    Request getReservation(int id);
}
