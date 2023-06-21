package com.homebroker.appservice.orders.requests;

import com.homebroker.domain.orders.objectvalue.OrderStatus;
import com.homebroker.domain.orders.objectvalue.OrderType;

public abstract class OrderRequest {
    public int walletId;
    public int quantity;
    public int price;
    public OrderType orderType;
    public OrderStatus orderStatus;

}
