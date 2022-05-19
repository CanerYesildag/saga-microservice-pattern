package com.order.orderservice.application;

import com.order.orderservice.application.event.OrderCreatedFatEvent;
import com.order.orderservice.domain.Order;
import com.order.orderservice.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);

        String transactionId = UUID.randomUUID().toString();
        OrderCreatedFatEvent orderCreatedFatEvent = new OrderCreatedFatEvent(transactionId, order);
        log.info("Publishing an order created orderCreatedFatEvent {}", orderCreatedFatEvent);
        publisher.publishEvent(orderCreatedFatEvent);

        return orderRepository.save(order);
    }

    public void updateOrderAsReceived(Long orderId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(order -> update(order, OrderStatus.RECEIVED),
                        () -> log.error("Order cannot update to status {}, Order {} not found", OrderStatus.RECEIVED, orderId));
    }

    public void cancelOrder(Long orderId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(order -> update(order, OrderStatus.CANCELED),
                        () -> log.error("Order cannot find with id: {}", orderId));
    }

    public void update(Order order, OrderStatus orderStatus) {
        order.setStatus(orderStatus);
        orderRepository.save(order);
    }
}
