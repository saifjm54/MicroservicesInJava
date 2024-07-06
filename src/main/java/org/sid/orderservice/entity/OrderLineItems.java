package org.sid.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderLineItems {
    private Integer quantity;

    private String menuItem;

    private String name;

    private Money itemPrice;

    private Money total;

    public OrderLineItems(OrderLineItems orderLineItems) {
        this.quantity = orderLineItems.quantity;
        this.menuItem = orderLineItems.menuItem;
        this.name = orderLineItems.name;
    }

    public LineItemQuantityChange lineItemQuantityChange(OrderRevision orderRevision) {
        this.quantity = orderRevision.getQuantity();
        return new LineItemQuantityChange(this.total,total());
    }

    public Money total(){
        return Money.multiply(itemPrice,this.quantity);
    }

    public void updateLineItems(OrderRevision orderRevision) {
        this.quantity = orderRevision.getQuantity();

    }
}
