package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        if (!stockList.contains(stock)) {
            stockList.add(stock);
            viewers.put(stock.getCode(), new ArrayList<>());
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        if (!viewers.containsKey(code) || !stockList.stream().anyMatch(s -> s.getCode().equals(code))) {
            Logger.errorRegister(code);
            return;
        }
    
        List<StockViewer> viewerList = viewers.get(code);
    
        // Không cho phép trùng StockAlertView
        if (stockViewer instanceof StockAlertView) {
            for (StockViewer viewer : viewerList) {
                if (viewer instanceof StockAlertView) {
                    Logger.errorRegister(code);
                    return;
                }
            }
        }
    
        // Không cho phép trùng StockRealtimePriceView
        if (stockViewer instanceof StockRealtimePriceView) {
            for (StockViewer viewer : viewerList) {
                if (viewer instanceof StockRealtimePriceView) {
                    Logger.errorRegister(code);
                    return;
                }
            }
        }
    
        // Không cho đăng ký lại cùng object
        if (viewerList.contains(stockViewer)) {
            Logger.errorRegister(code);
            return;
        }
    
        viewerList.add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        if (!viewers.containsKey(code) || !viewers.get(code).contains(stockViewer)) {
            Logger.errorUnregister(code);
            return;
        }
        viewers.get(code).remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) {
        List<StockViewer> stockViewers = viewers.get(stockPrice.getCode());
        if (stockViewers == null || stockViewers.isEmpty()) {
            return; 
        }
    
        for (StockViewer viewer : stockViewers) {
            viewer.onUpdate(stockPrice);
        }
    }

    
    
}
