package com.order.orderservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderservice.application.OrderService;
import com.order.orderservice.application.event.BilledOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BilledOrderEventHandler {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @SneakyThrows
    @KafkaListener(topics = {"${payment.order-billed-order}"})
    public void handle(@Payload String billedOrderEvent) {
        BilledOrderEvent billedOrder = objectMapper.readValue(billedOrderEvent, BilledOrderEvent.class);
        orderService.updateOrderAsReceived(billedOrder.getOrder().getId());
    }
}
