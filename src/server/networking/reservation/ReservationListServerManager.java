package server.networking.reservation;

import server.model.reservation.ReservationHandler;
import shared.networking.serverInterfaces.ReservationServer;
import shared.utils.reservation.Reservation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ReservationListServerManager implements ReservationServer {

    private ReservationHandler reservationHandler;
    private ArrayList<Reservation> reservations;

    public ReservationListServerManager(ReservationHandler reservationHandler) throws RemoteException {
        this.reservationHandler = reservationHandler;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void reserve(Reservation reservation) throws RemoteException {
        reservations.add(reservation);
    }

    @Override
    public void clearReservation(Reservation reservation) throws RemoteException {
        reservations.remove(reservation);
    }

    @Override
    public Reservation getReservation(int id) throws RemoteException {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Reservation> getReservations() throws RemoteException {
        return reservations;
    }
}
