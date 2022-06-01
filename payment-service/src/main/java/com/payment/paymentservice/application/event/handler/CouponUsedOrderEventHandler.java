package com.payment.paymentservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentservice.application.PaymentService;
import com.payment.paymentservice.application.event.CouponUsedOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponUsedOrderEventHandler {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @SneakyThrows
    @KafkaListener(topics = {"${coupon.order-coupon-used}"})
    public void handle(@Payload String couponUsedOrderEvent) {
        CouponUsedOrderEvent couponUsedOrder = objectMapper.readValue(couponUsedOrderEvent, CouponUsedOrderEvent.class);
        paymentService.pay(couponUsedOrder.getOrder(), couponUsedOrder.getTransactionId());
    }
}
