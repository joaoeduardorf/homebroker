package com.homebroker.appservice.orders.responses;

import com.homebroker.domain.orders.objectvalue.OrderStatus;
import com.homebroker.domain.orders.objectvalue.OrderType;

import java.util.UUID;

public class OrderResponse {
    private UUID orderId;
    private int walletId;
    private int quantity;
    private int price;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private long timestampRequested;
    private long timestampQueued;
    private long timestampOrdered;
    private long timestampExecuted;
    //    private List<long> timestampPartExecuted;
//    private long timestampExpired;
    private long timestampCanceled;
    private long timestampRejected;

    public OrderResponse(UUID orderId, int walletId, int quantity, int price, OrderType orderType, OrderStatus orderStatus , long timestampRequested) {
        this.orderId = orderId;
        this.walletId = walletId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.timestampRequested = timestampRequested;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public int getWalletId() {
        return walletId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public long getTimestampRequested() {
        return timestampRequested;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public long getTimestampQueued() {
        return timestampQueued;
    }

    public long getTimestampOrdered() {
        return timestampOrdered;
    }

    public long getTimestampExecuted() {
        return timestampExecuted;
    }

    public long getTimestampCanceled() {
        return timestampCanceled;
    }

    public long getTimestampRejected() {
        return timestampRejected;
    }
}