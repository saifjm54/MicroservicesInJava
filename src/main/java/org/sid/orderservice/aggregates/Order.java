package org.sid.orderservice.aggregates;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.command.ConfirmReviseOrder;
import org.sid.orderservice.command.CreateOrderCommand;
import org.sid.orderservice.command.OrderCommand;
import org.sid.orderservice.command.ReviseOrder;
import org.sid.orderservice.dto.DeliveryInformation;
import org.sid.orderservice.dto.OrderDetails;
import org.sid.orderservice.dto.PaymentInformation;
import org.sid.orderservice.entity.*;
import org.sid.orderservice.event.OrderCreated;
import org.sid.orderservice.event.OrderRevised;
import org.sid.orderservice.event.OrderRevisionProposed;
import org.sid.orderservice.exceptions.OrderMinimumNotMetException;
import org.sid.orderservice.exceptions.UnsupportedStateTransitionException;

import java.util.Collections;
import java.util.List;

import static io.eventuate.EventUtil.events;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order extends ReflectiveMutableCommandProcessingAggregate<Order, OrderCommand> {


    private Long id;
    private OrderState state;
    private Long consumerId;
    private Long restaurantId;
    private OrderLineItems orderLineItems;
    private DeliveryInformation deliveryInformation;
    private PaymentInformation paymentInformation;
    private  static Money orderMinimum = new Money(0.0);


    public List<Event> process(CreateOrderCommand command){
        return events(new OrderCreated(command.getOrderDetails()));
    }
    public void apply(OrderCreated event){
        OrderDetails orderDetails = event.getOrderDetails();
        this.orderLineItems = new OrderLineItems(orderDetails.getLineItems());
        this.orderMinimum = orderDetails.getOrderMinimum();
        this.state = OrderState.APPROVAL_PENDING;
    }

    public List<Event> process(ReviseOrder command) {
        OrderRevision orderRevision = command.getOrderRevision();
        switch (state) {
            case APPROVED:
                LineItemQuantityChange change = orderLineItems.lineItemQuantityChange(orderRevision);
                if(change.newOrderTotal.isGreaterThanOrEqual(orderMinimum)){
                    throw new OrderMinimumNotMetException();
                }
                return Collections.singletonList(new OrderRevisionProposed(orderRevision,change.currentOrderTotal,change.newOrderTotal));
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public void apply(OrderRevisionProposed event){
        this.state = OrderState.REVISION_PENDING;
    }

    public List<Event> process(ConfirmReviseOrder command){
        OrderRevision orderRevision = command.getOrderRevision();
        switch (state) {
            case REVISION_PENDING:
                LineItemQuantityChange licd = orderLineItems.lineItemQuantityChange(orderRevision);
                return Collections.singletonList(new OrderRevised(orderRevision,licd
                        .currentOrderTotal,licd.newOrderTotal));
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public void apply(OrderRevised event) {
        OrderRevision orderRevision = event.getOrderRevision();
        if(!orderRevision.getRevisedLineItemQuantites().isEmpty()){
            orderLineItems.updateLineItems(orderRevision);
        }
        this.state = OrderState.APPROVED;
    }


}
