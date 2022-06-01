package com.stock.stockservice.application.event;

import com.stock.stockservice.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CancelBilledOrderEvent {

    private String transactionId;
    private final Order order;
}
