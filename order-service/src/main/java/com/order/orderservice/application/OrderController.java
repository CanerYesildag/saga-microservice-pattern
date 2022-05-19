package com.order.orderservice.application;

import com.order.orderservice.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        Order createdOrder = service.createOrder(order);
        log.info("Order is created with id: {}", createdOrder.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
