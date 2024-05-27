package shared.networking.serverInterfaces;

import shared.utils.reservation.Reservation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReservationServer extends Remote {
    void reserve(Reservation reservation) throws RemoteException;

    void clearReservation(Reservation reservation) throws RemoteException;

    Reservation getReservation(int id) throws RemoteException;

    ArrayList<Reservation> getReservations() throws RemoteException;

    ArrayList<Reservation> getReservationsByUsername(String username) throws RemoteException;

}
