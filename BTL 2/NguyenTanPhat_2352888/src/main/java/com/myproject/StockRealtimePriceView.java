package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String stockCode = stockPrice.getCode();
        double newPrice = stockPrice.getAvgPrice();

        // Nếu đã có giá trước đó và giá thay đổi ⇒ mới log realtime
        if (lastPrices.containsKey(stockCode) && lastPrices.get(stockCode) != newPrice) {
            Logger.logRealtime(stockCode, newPrice);
        }
        lastPrices.put(stockCode, newPrice);
    }

}
