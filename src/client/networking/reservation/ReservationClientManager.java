package client.networking.reservation;

import client.model.Reservation.ReservationList;
import client.networking.GetServer;
import server.database.reservation.ReservationDAO;
import server.database.reservation.ReservationDAOManager;
import shared.networking.serverInterfaces.LoginServer;
import shared.networking.serverInterfaces.Server;
import shared.utils.reservation.Reservation;
import shared.utils.table.Table;
import shared.utils.user.Customer;

import java.beans.PropertyChangeSupport;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReservationClientManager implements ReservationClient{
    private ReservationDAO reservationDAO;

    public ReservationClientManager() {
        reservationDAO = new ReservationDAOManager();
    }


    @Override
    public void clearReservation(Reservation reservation) {
        reservationDAO.deleteReservation(reservation.getId());
    }

    @Override
    public void reserveTable(Reservation reservation, Table table, Customer customer) {

    }
}

