package com.coupon.couponservice.application.event.handler;

import com.coupon.couponservice.application.CouponService;
import com.coupon.couponservice.application.event.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedEventHandler {

    private final ObjectMapper objectMapper;
    private final CouponService couponService;

    @SneakyThrows
    @KafkaListener(topics = {"${checkout.order-created}"})
    public void handle(@Payload String orderCreatedEvent) {
        OrderCreatedEvent orderCreated = objectMapper.readValue(orderCreatedEvent, OrderCreatedEvent.class);
        couponService.use(orderCreated.getOrder(), orderCreated.getTransactionId());
    }
}
