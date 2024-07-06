package org.sid.orderservice.handlers;

import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.sid.orderservice.event.CreditReserved;

@EventSubscriber(id = "orderServiceEventHandlers")
public class OrderServiceEventHandlers {

    @EventHandlerMethod
    public void creditReserved(EventHandlerContext<CreditReserved> ctx){
        CreditReserved event = ctx.getEvent();
    }
}
