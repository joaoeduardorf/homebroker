package com.homebroker.domain.orders.entities;

import com.homebroker.domain.orders.objectvalue.OrderStatus;
import com.homebroker.domain.orders.objectvalue.OrderType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "Orders")
public class Order {
    @Id
    private UUID orderId;
    private int walletId;
    private int quantity;
    private int quantityExecuted;
    private int quantityToExecute;
    private int price;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private long timestampRequested;
    private long timestampQueued;
    private long timestampExecuted;
    private long timestampCanceled;
    private long timestampRejected;
    public Order(int walletId, int quantity, int price, OrderType orderType, OrderStatus orderStatus) {
        this.orderId = UUID.randomUUID();
        this.walletId = walletId;
        this.quantity = quantity;
        this.quantityToExecute = quantity;
        this.price = price;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.timestampRequested = Instant.now().toEpochMilli();
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

    public int getWalletId() {
        return walletId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public long getTimestampQueued() {
        return timestampQueued;
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

    public int getQuantityExecuted() {
        return quantityExecuted;
    }
    public void setQuantityExecuted(int quantityExecuted) {
        this.quantityExecuted = quantityExecuted;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTimestampQueued(long timestampQueued) {
        this.orderStatus = OrderStatus.QUEUED;
        this.timestampQueued = timestampQueued;
    }

    public void setTimestampExecuted(long timestampExecuted) {
        this.orderStatus= OrderStatus.EXECUTED;
        this.timestampExecuted = timestampExecuted;
    }

    public void setTimestampCanceled(long timestampCanceled) {
        this.orderStatus = OrderStatus.CANCELED;
        this.timestampCanceled = timestampCanceled;
    }

    public void setTimestampRejected(long timestampRejected) {
        this.orderStatus = OrderStatus.REJECTED;
        this.timestampRejected = timestampRejected;
    }

    public void setQuantityToExecute(int quantity){
        this.quantityToExecute = quantity;
    }
    public int getQuantityToExecute() {
        return quantityToExecute;
    }
}
