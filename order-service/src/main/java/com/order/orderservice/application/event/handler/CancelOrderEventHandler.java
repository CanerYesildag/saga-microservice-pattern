package com.order.orderservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderservice.application.OrderService;
import com.order.orderservice.application.event.CancelOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelOrderEventHandler {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @SneakyThrows
    @KafkaListener(topics = {"${coupon.cancel-order}"})
    public void handle(@Payload String cancelOrderEvent) {
        CancelOrderEvent orderCancelEvent = objectMapper.readValue(cancelOrderEvent, CancelOrderEvent.class);
        orderService.cancelOrder(orderCancelEvent.getOrder().getId());
    }
}
