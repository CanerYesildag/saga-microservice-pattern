package com.coupon.couponservice.application.event;

import com.coupon.couponservice.domain.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedFatEvent {

    private String transactionId;
    private Order order;
}
