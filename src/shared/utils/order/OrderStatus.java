package shared.utils.order;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    ORDERED, PREPARING, READY, SERVED
}
