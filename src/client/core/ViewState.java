package client.core;

public class ViewState {
    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String tablename;
    private int capacity;
    private boolean status;

    public ViewState() {
        this.tablename = null;
        this.capacity = 0;
        this.status = false;
    }
}