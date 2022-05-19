package com.order.orderservice.application.event;

import com.order.orderservice.domain.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderEvent {

    private String transactionId;
    private Order order;
}
