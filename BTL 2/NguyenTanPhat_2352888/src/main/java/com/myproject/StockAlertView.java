package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private final Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public synchronized void onUpdate(StockPrice stockPrice) {
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();
        Double lastPrice = lastAlertedPrices.get(stockCode);

        boolean shouldAlert =
            (currentPrice >= alertThresholdHigh || currentPrice < alertThresholdLow) &&
            (lastPrice == null || !Double.valueOf(currentPrice).equals(lastPrice));

        if (shouldAlert) {
            if (currentPrice >= alertThresholdHigh) {
                alertAbove(stockCode, currentPrice);
            } else {
                alertBelow(stockCode, currentPrice);
            }
            lastAlertedPrices.put(stockCode, currentPrice);
        }
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }
}
