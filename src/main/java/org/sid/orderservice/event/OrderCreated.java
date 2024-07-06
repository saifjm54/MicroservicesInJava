package org.sid.orderservice.event;

import lombok.Data;
import org.sid.orderservice.dto.OrderDetails;

@Data
public class OrderCreated implements OrderEvent {
    OrderDetails orderDetails;
    public OrderCreated(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
