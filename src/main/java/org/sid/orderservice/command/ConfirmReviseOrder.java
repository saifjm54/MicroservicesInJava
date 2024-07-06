package org.sid.orderservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entity.OrderRevision;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmReviseOrder implements OrderCommand{

    private OrderRevision orderRevision;
}
