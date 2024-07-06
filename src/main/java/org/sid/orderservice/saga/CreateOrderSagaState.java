package org.sid.orderservice.saga;

import lombok.Data;
import org.sid.orderservice.command.CreateTicket;
import org.sid.orderservice.dto.CancelCreateTicket;
import org.sid.orderservice.dto.CreateTicketReply;
import org.sid.orderservice.dto.OrderDetails;
import org.sid.orderservice.dto.TicketDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class CreateOrderSagaState {

    private static final Logger log = LoggerFactory.getLogger(CreateOrderSagaState.class);
    private Long orderId;

    private OrderDetails orderDetails;

    private long ticketId;

    public Long getOrderId(){
        return orderId;
    }

    private CreateOrderSagaState(){

    }

    public CreateOrderSagaState(Long orderId, OrderDetails orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    CreateTicket makeCreateTicketCommand() {
        return new CreateTicket(getOrderDetails().getRestaurantId(),getOrderId(),makeTicketDetails(getOrderDetails()));
    }

    void handleCreateTicketReply(CreateTicketReply reply){
        log.debug("getTicketId{}",reply.getTicketId());
        setTicketId(reply.getTicketId());
    }

    CancelCreateTicket makeCancelCreateTicketCommand() {
        return new CancelCreateTicket(getOrderId());
    }

    private TicketDetails makeTicketDetails(OrderDetails orderDetails){

        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setConsumerId(orderDetails.getConsumerId());
        ticketDetails.setLineItems(orderDetails.getLineItems());
        ticketDetails.setRestaurantId(orderDetails.getRestaurantId());
        return ticketDetails;
    }
}
