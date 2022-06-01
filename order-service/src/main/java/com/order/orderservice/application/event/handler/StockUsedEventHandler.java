package com.order.orderservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderservice.application.OrderService;
import com.order.orderservice.application.event.StockUsedEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockUsedEventHandler {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @SneakyThrows
    @KafkaListener(topics = {"${stock.stock-used}"})
    public void handle(@Payload String stockUsedEvent) {
        StockUsedEvent stockUsed = objectMapper.readValue(stockUsedEvent, StockUsedEvent.class);
        orderService.updateOrderAsReceived(stockUsed.getOrder().getId());
    }
}
