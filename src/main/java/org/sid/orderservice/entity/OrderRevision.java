package org.sid.orderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRevision {
    private Integer quantity;
    private List<Integer> revisedLineItemQuantites = new ArrayList<>();

    public OrderRevision(Integer quantity) {

        this.quantity = quantity;
        this.revisedLineItemQuantites.add(quantity);
    }
}
