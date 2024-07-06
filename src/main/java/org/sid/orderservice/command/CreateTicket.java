package org.sid.orderservice.command;

import org.sid.orderservice.dto.TicketDetails;

public class CreateTicket implements OrderCommand{
    private Long restaurantId;
    private Long orderId;
    private TicketDetails ticketDetails;

    public CreateTicket(Long restaurantId, Long orderId, TicketDetails ticketDetails) {
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.ticketDetails = ticketDetails;
    }
}
