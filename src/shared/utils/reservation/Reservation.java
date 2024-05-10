package shared.utils.reservation;

import java.time.LocalDateTime;
import java.util.List;

public class Reservation {
    private String userName;
    private LocalDateTime dateTime;
    private int id;
    private List<String> occupiedTables;
    private String tempTable;

    public Reservation(String userName, LocalDateTime dateTime, List<String> occupiedTables) {
        this.userName = userName;
        this.dateTime = dateTime;
        this.occupiedTables = occupiedTables;
    }

    public Reservation(String tempTable, LocalDateTime dateTime) {
        this.tempTable = tempTable;
        this.dateTime = dateTime;
    }

    public Reservation(int id, String userName, LocalDateTime dateTime) {
        this.id = id;
        this.userName = userName;
        this.dateTime = dateTime;
    }

    private void verifyData(String username, LocalDateTime dateTime, List<String> occupiedTables) throws Exception {
        if (username == null) {
            throw new Exception("Cannot create a reservation without customer");
        }
        if (dateTime == null) {
            throw new Exception("Reservation Date missing");
        } else if (occupiedTables == null || occupiedTables.isEmpty()) {
            throw new Exception("Cannot create a reservation without Tables");
        }
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

    public List<String> getOccupiedTables() {
        return occupiedTables;
    }

    public String getTempTable() {
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

    public void setOccupiedTables(List<String> occupiedTables) {
        this.occupiedTables = occupiedTables;
    }

    public void setTempTable(String tempTable) {
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
