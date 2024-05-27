package shared.utils;

import java.io.Serializable;


public class Request implements Serializable {
    private String type;
    private Object object;


    public Request(String type, Object object){
        this.type = type;
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
