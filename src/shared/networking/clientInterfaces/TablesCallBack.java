package shared.networking.clientInterfaces;

import shared.utils.table.Table;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TablesCallBack extends Remote {
    void tablesCallBack(List<Table> tableList) throws RemoteException;
}
