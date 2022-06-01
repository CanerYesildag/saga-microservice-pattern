package com.stock.stockservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Stock {

    private Long id;
    private Long stockCount;
    private Long productId;
    private String productName;
    private String sellerName;

    public Stock decrease() {
        this.setStockCount(this.getStockCount() - 1);
        return this;
    }
}
