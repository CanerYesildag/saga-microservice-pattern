package com.order.orderservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderservice.application.OrderService;
import com.order.orderservice.application.event.BilledOrderFatEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BilledOrderFatEventHandler {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @SneakyThrows
    @KafkaListener(topics = {"${payment.order-billed-order}"})
    public void handle(@Payload String billedOrderFatEvent) {
        BilledOrderFatEvent billedOrderEvent = objectMapper.readValue(billedOrderFatEvent, BilledOrderFatEvent.class);
        orderService.updateOrderAsReceived(billedOrderEvent.getOrder().getId());
    }
}
