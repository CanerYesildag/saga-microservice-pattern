package com.stock.stockservice.application.event;

import com.stock.stockservice.domain.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockUsedEvent {

    private String transactionId;
    private Order order;
}
