package com.homebroker.appservice.orders.interfaces;

import com.homebroker.appservice.orders.requests.BuyOrderRequest;
import com.homebroker.appservice.orders.requests.OrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;

import java.util.List;

public interface OrderAppService {
//    OrderResponse buyOrder(BuyOrderRequest buyOrderRequest);
    OrderResponse executeOrder(OrderRequest sellOrderRequest);
    List<OrderResponse> getHistory();
}
