package org.sid.orderservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entity.OrderRevision;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviseOrder implements OrderCommand{

    OrderRevision orderRevision;
}
