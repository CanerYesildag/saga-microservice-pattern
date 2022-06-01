package com.coupon.couponservice.application;

import com.coupon.couponservice.application.event.CancelOrderEvent;
import com.coupon.couponservice.application.event.CouponUsedOrderEvent;
import com.coupon.couponservice.domain.Coupon;
import com.coupon.couponservice.domain.CouponStatus;
import com.coupon.couponservice.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void use(Order order, String transactionId) {
        Coupon coupon = couponRepository.getById(order.getCouponId());
        if (coupon.getCouponStatus() != CouponStatus.ACTIVE) {
            log.warn("Used or cancelled coupons can not be used");
            eventPublisher.publishEvent(new CancelOrderEvent(transactionId, order));
        }
        coupon.setCouponStatus(CouponStatus.USED);
        coupon.setUsedOrderId(order.getId());
        couponRepository.save(coupon);
        eventPublisher.publishEvent(new CouponUsedOrderEvent(transactionId, order));
    }

    public void cancelUsed(Order order, String transactionId) {
        Coupon coupon = couponRepository.getById(order.getCouponId());
        coupon.setCouponStatus(CouponStatus.ACTIVE);
        coupon.setUsedOrderId(null);
        couponRepository.save(coupon);
        eventPublisher.publishEvent(new CancelOrderEvent(transactionId, order));
    }
}
