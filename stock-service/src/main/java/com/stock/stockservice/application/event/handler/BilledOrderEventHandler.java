package com.stock.stockservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stockservice.application.StockService;
import com.stock.stockservice.application.event.BilledOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BilledOrderEventHandler {

    private final ObjectMapper objectMapper;
    private final StockService stockService;

    @SneakyThrows
    @KafkaListener(topics = {"${payment.billed-order}"})
    public void handle(@Payload String billedOrderEvent) {
        BilledOrderEvent billedOrder = objectMapper.readValue(billedOrderEvent, BilledOrderEvent.class);
        stockService.reduce(billedOrder.getOrder(), billedOrder.getTransactionId());
    }
}
