package org.sid.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelCreateTicket {
    private Long orderId;
}
