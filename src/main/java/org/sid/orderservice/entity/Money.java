package org.sid.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    private double amount;

    public static Money multiply(Money itemPrice, Integer quantity) {
        double amount = itemPrice.getAmount() * quantity;
        return new Money(amount);
    }

    public boolean isGreaterThanOrEqual(Money orderMinimum) {
        return amount >= orderMinimum.getAmount();
    }
}
