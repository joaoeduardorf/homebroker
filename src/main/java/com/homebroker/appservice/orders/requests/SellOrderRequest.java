package com.homebroker.appservice.orders.requests;

import com.homebroker.domain.orders.objectvalue.OrderStatus;
import com.homebroker.domain.orders.objectvalue.OrderType;

public class SellOrderRequest extends OrderRequest{
    private SellOrderRequest(){
        this.orderType = OrderType.SELL;
        this.orderStatus = OrderStatus.REQUESTED;
    }

}
