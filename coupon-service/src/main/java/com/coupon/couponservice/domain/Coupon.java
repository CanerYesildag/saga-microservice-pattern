package com.coupon.couponservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Coupon {

    private String id;
    private Long usedOrderId;
    private BigDecimal discount;
    private BigDecimal lowerLimit;
    private CouponStatus couponStatus;
}
