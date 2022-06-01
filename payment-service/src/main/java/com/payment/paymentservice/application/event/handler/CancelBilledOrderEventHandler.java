package com.payment.paymentservice.application.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentservice.application.PaymentService;
import com.payment.paymentservice.application.event.CancelBilledOrderEvent;
import com.payment.paymentservice.application.event.CouponUsedOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelBilledOrderEventHandler {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @SneakyThrows
    @KafkaListener(topics = {"${stock.stock-used-cancel}"})
    public void handle(@Payload String cancelBilledOrderEvent) {
        CancelBilledOrderEvent cancelBilledOrder = objectMapper.readValue(cancelBilledOrderEvent, CancelBilledOrderEvent.class);
        paymentService.cancelPayment(cancelBilledOrder.getOrder(), cancelBilledOrder.getTransactionId());
    }
}
