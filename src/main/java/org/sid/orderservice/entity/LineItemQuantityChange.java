package org.sid.orderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItemQuantityChange {

    public Money currentOrderTotal;

    public Money newOrderTotal;

}
