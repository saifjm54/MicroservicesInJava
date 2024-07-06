package org.sid.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entity.OrderLineItems;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetails {

    private long tickerId;

    private long customerId;

    private long restaurantId;

    private Long consumerId;

    private OrderLineItems lineItems;
}
