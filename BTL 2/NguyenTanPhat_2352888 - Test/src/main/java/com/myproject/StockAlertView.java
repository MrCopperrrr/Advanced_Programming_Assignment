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
        else if (currentPrice <= alertThresholdLow) {
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



// package com.myproject;

// import java.util.HashMap;
// import java.util.Map;

// public class StockAlertView implements StockViewer {
//     private double alertThresholdHigh;
//     private double alertThresholdLow;
//     private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

//     public StockAlertView(double highThreshold, double lowThreshold) {
//         this.alertThresholdHigh=highThreshold;
//         this.alertThresholdLow=lowThreshold;
//     }

//     @Override
// public synchronized void onUpdate(StockPrice stockPrice) {
//     if (stockPrice == null) return;

//     String stockCode = stockPrice.getCode();
//     double price = stockPrice.getAvgPrice();

//     if (!lastAlertedPrices.containsKey(stockCode)) {
//         // Chưa từng alert → alert nếu vượt ngưỡng
//         if (price >= alertThresholdHigh) {
//             alertAbove(stockCode, price);
//             lastAlertedPrices.put(stockCode, price);
//         } else if (price <= alertThresholdLow) {
//             alertBelow(stockCode, price);
//             lastAlertedPrices.put(stockCode, price);
//         }
//     } else {
//         double lastPrice = lastAlertedPrices.get(stockCode);

//         // Chỉ alert nếu vượt ngưỡng và khác lần trước
//         if (price >= alertThresholdHigh && price != lastPrice) {
//             alertAbove(stockCode, price);
//             lastAlertedPrices.put(stockCode, price);
//         } else if (price <= alertThresholdLow && price != lastPrice) {
//             alertBelow(stockCode, price);
//             lastAlertedPrices.put(stockCode, price);
//         }
//     }
// }

//     private void alertAbove(String stockCode, double price) {
//         // TODO: Call Logger to log the alert
//         Logger.logAlert(stockCode, price);    }

//     private void alertBelow(String stockCode, double price) {
//         // TODO: Call Logger to log the alert
//         Logger.logAlert(stockCode, price);
//         }
// }



// package com.myproject;

// import java.util.HashMap;
// import java.util.Map;

// public class StockAlertView implements StockViewer {
//     private double alertThresholdHigh;
//     private double alertThresholdLow;
//     private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

//     public StockAlertView(double highThreshold, double lowThreshold) {
//         // TODO: Implement constructor
//         this.alertThresholdHigh = highThreshold;
//         this.alertThresholdLow = lowThreshold;
//     }

//     @Override
//     public void onUpdate(StockPrice stockPrice) {
//         // TODO: Implement alert logic based on threshold conditions
//         String code = stockPrice.getCode();
//         double price = stockPrice.getAvgPrice();

//         Double lastAlerted = lastAlertedPrices.get(code);

//         // Kiểm tra nếu vượt ngưỡng cao và chưa cảnh báo hoặc giá đã thay đổi
//         if (price >= alertThresholdHigh &&
//             (lastAlerted == null || price != lastAlerted)) {  
//             alertAbove(code, price);
//             lastAlertedPrices.put(code, price);
//         }

//         // Kiểm tra nếu dưới ngưỡng thấp và chưa cảnh báo hoặc giá đã thay đổi
//         else if (price <= alertThresholdLow &&
//             (lastAlerted == null || price != lastAlerted)) { 
//             alertBelow(code, price);
//             lastAlertedPrices.put(code, price);
//         }
//         else {
//             // Giá đã quay về vùng an toàn → reset alert
//             lastAlertedPrices.remove(code);
//         }
     
//     }

//     private void alertAbove(String stockCode, double price) {
//         // TODO: Call Logger to log the alert
//         Logger.logAlert(stockCode, price);
//     }
    
//     private void alertBelow(String stockCode, double price) {
//         // TODO: Call Logger to log the alert
//         Logger.logAlert(stockCode, price);
//     }
    
// }