package com.homebroker.domain.ordersbook;

import com.homebroker.domain.orders.entities.Order;
import com.homebroker.domain.orders.objectvalue.OrderType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;
@Document(collection = "Transactions")
public class Transaction {

    @Id
    private UUID transactionId;
    private UUID buyerOrderId;
    private UUID sellerOrderId;
    private int buyerWalletId;
    private int sellerWalletId;
    private int quantity;
    private double price;
    private long timestamp;

    public Transaction(){}
    private Transaction(UUID buyerOrderId, UUID sellerOrderId,int buyerWalletId, int sellerWalletId, int quantity, double price) {
        this.transactionId = UUID.randomUUID();
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.buyerWalletId = buyerWalletId;
        this.sellerWalletId = sellerWalletId;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = Instant.now().toEpochMilli();
    }
    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getSellerWalletId() {
        return sellerWalletId;
    }

    public int getBuyerWalletId() {
        return buyerWalletId;
    }

    public UUID getBuyerOrderId() {
        return buyerOrderId;
    }

    public UUID getSellerOrderId() {
        return sellerOrderId;
    }

    public static Transaction createTransaction(Order order, Order orderByList, int quantity) {
        if (order.getOrderType() == OrderType.BUY) {
            return new Transaction(order.getOrderId(), orderByList.getOrderId(), order.getWalletId(), orderByList.getWalletId(), quantity, orderByList.getPrice());
        } else {
            return  new Transaction(orderByList.getOrderId(), order.getOrderId(), orderByList.getWalletId(), order.getWalletId(), quantity, order.getPrice());
        }
    }
}