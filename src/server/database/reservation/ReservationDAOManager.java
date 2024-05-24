package server.database.reservation;

import server.database.DatabaseConnection;
import shared.utils.Request;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationDAOManager implements ReservationDAO {

    private Connection connection =  new DatabaseConnection().getConnection();
    private static ReservationDAOManager instance;
    private static Lock lock = new ReentrantLock();

    public ReservationDAOManager() throws SQLException {

    }

    public static ReservationDAO getInstance() throws SQLException {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ReservationDAOManager();
                }
            }
        }
        return instance;
    }

    @Override
    public Request addReservation(String username, LocalDateTime localDateTime, String tableName) throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
            // Start transaction
            connection.setAutoCommit(false);

            // Insert reservation
            PreparedStatement reservationStatement = connection.prepareStatement(
                    "INSERT INTO reservation (table_number, customer_id, reservation_time) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            reservationStatement.setString(1, tableName);
            reservationStatement.setString(2, username);
            reservationStatement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            reservationStatement.executeUpdate();

            // Get the generated reservation_id
            ResultSet generatedKeys = reservationStatement.getGeneratedKeys();
            int reservationId = 0;
            if (generatedKeys.next()) {
                reservationId = generatedKeys.getInt(1);
            }

            // Update table status
            PreparedStatement tableUpdateStatement = connection.prepareStatement(
                    "UPDATE tables SET isOccupied = true WHERE table_name = ?");
            tableUpdateStatement.setString(1, tableName);
            tableUpdateStatement.executeUpdate();

            // Commit transaction
            connection.commit();

            System.out.println("Reservation added successfully with ID: " + reservationId);
            return new Request("Table is reserved", null);

        } catch (SQLException e) {
            // Rollback transaction if an error occurs
            if (connection != null) {
                connection.rollback();
            }
            return new Request("Username doesnt exist", null);

        } finally {
            // Close connection in finally block to ensure it's always closed
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public Request deleteReservation(int id) {

        try {
            connection = DatabaseConnection.getConnection();
            // Start transaction
            connection.setAutoCommit(false);

            // Check if the reservation exists
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT * FROM reservation WHERE reservation_id = ?");
            checkStatement.setInt(1, id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return new Request("Reservation not found", null);
            }

            // Delete reservation
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM reservation WHERE reservation_id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();

            // Update table status
            PreparedStatement tableUpdateStatement = connection.prepareStatement(
                    "UPDATE tables SET isOccupied = false WHERE table_number = ?");
            tableUpdateStatement.setString(1, resultSet.getString("table_number"));
            tableUpdateStatement.executeUpdate();

            // Commit transaction
            connection.commit();

            return new Request("Reservation deleted successfully", null);

        } catch (SQLException e) {
            // Rollback transaction if an error occurs
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return new Request("Failed to delete reservation", null);

        } finally {
            // Close connection in finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Request updateReservation(int id, String username, LocalDateTime localDateTime, String tableName) {
        try {
            connection = DatabaseConnection.getConnection();
            // Start transaction
            connection.setAutoCommit(false);

            // Check if the reservation exists
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT * FROM reservation WHERE reservation_id = ?");
            checkStatement.setInt(1, id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return new Request("Reservation not found", null);
            }

            // Update reservation
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE reservation SET table_number = ?, customer_id = ?, reservation_time = ? WHERE reservation_id = ?");
            updateStatement.setString(1, tableName);
            updateStatement.setString(2, username);
            updateStatement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            updateStatement.setInt(4, id);
            updateStatement.executeUpdate();

            // Update table status
            PreparedStatement tableUpdateStatement = connection.prepareStatement(
                    "UPDATE tables SET isOccupied = true WHERE table_number = ?");
            tableUpdateStatement.setString(1, tableName);
            tableUpdateStatement.executeUpdate();

            // Commit transaction
            connection.commit();

            return new Request("Reservation updated successfully", null);

        } catch (SQLException e) {
            // Rollback transaction if an error occurs
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return new Request("Failed to update reservation", null);

        } finally {
            // Close connection in finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Request getReservation(String username) {
        return null;
    }

    @Override
    public Request getReservation(int id) {
        return null;
    }
}
