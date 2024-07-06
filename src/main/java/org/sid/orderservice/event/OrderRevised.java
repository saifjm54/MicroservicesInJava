package org.sid.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entity.Money;
import org.sid.orderservice.entity.OrderRevision;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRevised implements OrderEvent{
    private OrderRevision orderRevision;
    private Money currentOrderTotal;
    private Money newOrderTotal;

}
