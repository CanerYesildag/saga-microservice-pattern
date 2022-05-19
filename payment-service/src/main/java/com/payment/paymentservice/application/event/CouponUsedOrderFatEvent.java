package com.payment.paymentservice.application.event;

import com.payment.paymentservice.domain.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CouponUsedOrderFatEvent {

    private String transactionId;
    private Order order;
}
