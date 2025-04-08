package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private final Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();

        // Lấy giá lần trước đã alert (nếu có)
        Double lastPrice = lastAlertedPrices.get(stockCode);

        // Nếu vượt ngưỡng cao
        if (currentPrice >= alertThresholdHigh) {
            if (lastPrice == null || lastPrice != currentPrice) {
                alertAbove(stockCode, currentPrice);
                lastAlertedPrices.put(stockCode, currentPrice);
            }
        }

        // Nếu thấp hơn ngưỡng thấp
        else if (currentPrice < alertThresholdLow) {
            if (lastPrice == null || lastPrice != currentPrice) {
                alertBelow(stockCode, currentPrice);
                lastAlertedPrices.put(stockCode, currentPrice);
            }
        }

        // Nếu trong vùng an toàn (không alert), reset trạng thái
        else {
            lastAlertedPrices.remove(stockCode);
        }
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }
}

