package server.database.reservation;

import server.database.DatabaseConnection;
import shared.utils.Request;
import shared.utils.reservation.Reservation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationDAOManager implements ReservationDAO {

    private Connection connection = DatabaseConnection.getInstance().getConnection();
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
            connection = DatabaseConnection.getInstance().getConnection();
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
            connection = DatabaseConnection.getInstance().getConnection();
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
    public ArrayList<Reservation> getAllReservations() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getString("table_number"),
                        resultSet.getString("customer_id"),
                        resultSet.getTimestamp("reservation_time").toLocalDateTime()
                );
                reservations.add(reservation);
            }

            return reservations;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

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
            connection = DatabaseConnection.getInstance().getConnection();
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
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE customer_id = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getString("table_number"),
                        resultSet.getString("customer_id"),
                        resultSet.getTimestamp("reservation_time").toLocalDateTime()
                );
                reservations.add(reservation);
            }

            return new Request("Reservations found", reservations);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Request("Failed to get reservations", null);

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
    public ArrayList<Reservation> getReservationsByUsername(String username) {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE customer_id = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getString("table_number"),
                        resultSet.getString("customer_id"),
                        resultSet.getTimestamp("reservation_time").toLocalDateTime()
                );
                reservations.add(reservation);
            }

            return reservations;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

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
    public Request getReservation(int id) {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE reservation_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getString("table_number"),
                        resultSet.getString("customer_id"),
                        resultSet.getTimestamp("reservation_time").toLocalDateTime()
                );
                return new Request("Reservation found", reservation);
            } else {
                return new Request("Reservation not found", null);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeReservation(int id) {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            // Start transaction
            connection.setAutoCommit(false);

            // Check if the reservation exists
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT * FROM reservation WHERE reservation_id = ?");
            checkStatement.setInt(1, id);
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                return;
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
}