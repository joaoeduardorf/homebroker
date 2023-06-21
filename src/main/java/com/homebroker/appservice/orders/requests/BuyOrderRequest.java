package com.homebroker.appservice.orders.requests;

import com.homebroker.domain.orders.objectvalue.OrderStatus;
import com.homebroker.domain.orders.objectvalue.OrderType;

public class BuyOrderRequest extends OrderRequest{
    private BuyOrderRequest(){
        orderType = OrderType.BUY;
        orderStatus = OrderStatus.REQUESTED;
    }

}
