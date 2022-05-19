package com.payment.paymentservice.application.event;

import com.payment.paymentservice.domain.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CancelCouponUseEvent {

    private String transactionId;
    private Order order;
}
