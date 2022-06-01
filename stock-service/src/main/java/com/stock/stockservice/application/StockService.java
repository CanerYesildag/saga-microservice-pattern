package com.stock.stockservice.application;

import com.stock.stockservice.application.event.CancelBilledOrderEvent;
import com.stock.stockservice.application.event.StockUsedEvent;
import com.stock.stockservice.domain.Order;
import com.stock.stockservice.domain.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void reduce(Order order, String transactionId) {
        /*
         * Any stock business logic
         */

        Optional<Stock> stock = stockRepository.findByProductId(order.getProductId())
                .filter(stock1 -> stock1.getStockCount() > 1)
                .map(Stock::decrease);

        if (stock.isPresent()) {
            stockRepository.save(stock.get());
            eventPublisher.publishEvent(new StockUsedEvent(transactionId, order));
        } else {
            eventPublisher.publishEvent(new CancelBilledOrderEvent(transactionId, order));
        }

    }
}
