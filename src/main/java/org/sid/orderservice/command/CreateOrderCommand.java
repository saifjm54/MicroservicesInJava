package org.sid.orderservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.dto.OrderDetails;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderCommand implements OrderCommand {

    OrderDetails orderDetails;
    public OrderDetails getOrderDetails(){
      return orderDetails;
    }
}
