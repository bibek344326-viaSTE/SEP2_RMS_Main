package shared.utils.reservation;

import shared.utils.table.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Reservation implements Serializable {
    private String userName;
    private LocalDateTime dateTime;
    private int id;
    private List<Table> occupiedTables;
    private Table tempTable;

    public Reservation(String userName, LocalDateTime dateTime, List<Table> occupiedTables) {
        this.userName = userName;
        this.dateTime = dateTime;
        this.occupiedTables = occupiedTables;
    }

    public Reservation(Table tempTable, LocalDateTime dateTime, String userName) {
        this.tempTable = tempTable;
        this.dateTime = dateTime;
        this.userName = userName;
    }

    public Reservation(int id, String userName, LocalDateTime dateTime) {
        this.id = id;
        this.userName = userName;
        this.dateTime = dateTime;
    }

    public Reservation(int reservationId, String tablename, String customerId, LocalDateTime reservationTime) {
        this.id = reservationId;
        this.userName = customerId;
        this.dateTime = reservationTime;
    }


    public Table getTable(){
        return tempTable;
    }
    public String getUserName() {
        return userName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getId() {
        return id;
    }

    public List<Table> getOccupiedTables() {
        return occupiedTables;
    }

    public Table getTempTable() {
        return tempTable;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOccupiedTables(List<Table> occupiedTables) {
        this.occupiedTables = occupiedTables;
    }

    public void setTempTable(Table tempTable) {
        this.tempTable = tempTable;
    }

    @Override
    public String toString() {
        return "reservation{" +
                "userName='" + userName + '\'' +
                ", dateTime=" + dateTime +
                ", id=" + id +
                ", occupiedTables=" + occupiedTables +
                ", tempTable='" + tempTable + '\'' +
                '}';
    }
}
