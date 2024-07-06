package org.sid.orderservice.exceptions;

import lombok.NoArgsConstructor;
public class OrderMinimumNotMetException extends RuntimeException{
    public OrderMinimumNotMetException() {
        super("Order minimum not met");
    }
}
