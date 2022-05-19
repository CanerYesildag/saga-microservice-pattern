package com.order.orderservice.domain;

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
    private String couponId;

}