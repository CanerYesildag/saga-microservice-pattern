package com.payment.paymentservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentservice.application.PaymentService;
import com.payment.paymentservice.application.event.CouponUsedOrderFatEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponUsedOrderFatEventHandler {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @SneakyThrows
    @KafkaListener(topics = {"${coupon.order-coupon-used}"})
    public void handle(@Payload String couponUsedOrderFatEvent) {
        CouponUsedOrderFatEvent usedOrderFatEvent = objectMapper.readValue(couponUsedOrderFatEvent, CouponUsedOrderFatEvent.class);
        paymentService.pay(usedOrderFatEvent.getOrder(), usedOrderFatEvent.getTransactionId());
    }
}
