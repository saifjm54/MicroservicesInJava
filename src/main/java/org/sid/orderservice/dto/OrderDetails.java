package org.sid.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entity.Money;
import org.sid.orderservice.entity.OrderLineItems;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {

    private Long restaurantId;
    private OrderLineItems lineItems;
    private Money orderMinimum;
    private Long consumerId;
}
