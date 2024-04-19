package com.example.report.order.common;

public enum StockStatus {
    AVAILABLE(0),
    NOT_AVAILABLE(1);
    private int id;
    StockStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
