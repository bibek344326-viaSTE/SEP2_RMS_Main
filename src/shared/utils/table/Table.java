package shared.utils.table;

import java.io.Serializable;

public class Table implements Serializable {
    private String tableName;
    private int tableCapacity;
    private boolean isOccupied;

    public Table(String tableName, int tableCapacity) {
        this.tableName = tableName;
        this.tableCapacity = tableCapacity;
        isOccupied = false;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(int tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "Table [tableName= " + tableName + ", tableCapacity= " + tableCapacity;
    }
}
