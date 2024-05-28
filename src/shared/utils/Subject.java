package shared.utils;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

public interface Subject extends Serializable {

    void addListener(String eventName, PropertyChangeListener listener);
    void removeListener(String eventName, PropertyChangeListener listener);

}
