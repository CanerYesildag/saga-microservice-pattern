package com.payment.paymentservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class Payment {

    private Long id;
    private Long usedOrderId;
    private BigDecimal price;
    private PaymentStatus paymentStatus;

}
