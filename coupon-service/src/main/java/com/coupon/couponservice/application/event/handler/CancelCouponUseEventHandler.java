package com.coupon.couponservice.application.event.handler;

import com.coupon.couponservice.application.CouponService;
import com.coupon.couponservice.application.event.CancelCouponUseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelCouponUseEventHandler {

    private final ObjectMapper objectMapper;
    private final CouponService couponService;

    @SneakyThrows
    @KafkaListener(topics = {"${payment.coupon-used-cancel}"})
    public void handle(@Payload String cancelCouponUseEvent) {
        CancelCouponUseEvent couponUseCancelEvent = objectMapper.readValue(cancelCouponUseEvent, CancelCouponUseEvent.class);
        couponService.cancelUsed(couponUseCancelEvent.getOrder(), couponUseCancelEvent.getTransactionId());
    }
}
