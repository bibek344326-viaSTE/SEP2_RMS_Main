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
    private boolean remove;
    private String customerName;
    private String password;
    private int Id;

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    private String status2;

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getCustomerName() {return customerName;}
    public String getPassword() {return password;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public void setPassword(String password) {this.password = password;}
    public int getId() {return Id;}
    public void setId(int Id) {this.Id = Id;}

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemType() {
        return menuItemType;
    }

    public void setMenuItemType(String menuItemType) {
        this.menuItemType = menuItemType;
    }
    public void setRemove(boolean remove) {this.remove = remove;}
    public boolean isRemove() {return remove;}

    private String menuItemName;
    private String menuItemType;

    public ViewState() {
        this.tablename = null;
        this.capacity = 0;
        this.status = false;
        this.menuItemName = null;
        this.menuItemType = null;
        this.remove =  false;
        this.customerName = null;
        this.password = null;
        this.Id = 0;
        this.status2 = null;
    }
}