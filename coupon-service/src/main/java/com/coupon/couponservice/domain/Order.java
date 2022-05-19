package com.coupon.couponservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Order {

    private Long id;
    private Long productId;
    private Long quantity;
    private BigDecimal price;
    private OrderStatus status;
    private Long couponId;

}