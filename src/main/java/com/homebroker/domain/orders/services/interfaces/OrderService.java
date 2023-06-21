package com.homebroker.domain.orders.services.interfaces;

import com.homebroker.domain.orders.entities.Order;

import java.util.List;

public interface OrderService {
    public Order addOrder(Order order);
    public List<Order> getBuyOrdersAvailable();
    public List<Order> getSellOrdersAvailable();
    void queue(Order order);
    void execute(Order order);
    void cancel(String orderId);
}
