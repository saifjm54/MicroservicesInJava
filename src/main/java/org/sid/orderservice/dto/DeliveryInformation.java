package org.sid.orderservice.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DeliveryInformation {
    ZonedDateTime deliveryTime;
}
