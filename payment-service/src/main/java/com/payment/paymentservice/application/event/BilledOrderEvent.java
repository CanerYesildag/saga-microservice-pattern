package com.payment.paymentservice.application.event;

import com.payment.paymentservice.domain.Order;
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
