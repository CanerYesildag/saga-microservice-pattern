package com.order.orderservice.application.event;

import com.order.orderservice.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BilledOrderEvent {

    private String transactionId;
    private final Order order;
}
