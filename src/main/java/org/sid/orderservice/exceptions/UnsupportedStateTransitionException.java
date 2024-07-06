package org.sid.orderservice.exceptions;

import org.sid.orderservice.entity.OrderState;

public class UnsupportedStateTransitionException extends RuntimeException {
    public UnsupportedStateTransitionException(OrderState state) {
        super("Unsupported state transition: " + state);
    }
}
