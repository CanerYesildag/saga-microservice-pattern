package com.order.orderservice.application.event;

import com.order.orderservice.domain.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {

    private String transactionId;
    private Order order;
}
