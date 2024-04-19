package com.example.report.order.common;

public enum OrderStatus {

    PLACED(0),
    SHIPPED(1),
    CANCELLED(2);

    private int id;
    OrderStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
