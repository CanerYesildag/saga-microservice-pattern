package com.payment.paymentservice.application.event;


import com.payment.paymentservice.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CancelBilledOrderEvent {

    private final Order order;
    private String transactionId;
}
