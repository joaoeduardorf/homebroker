package com.homebroker.appservice.orders.interfaces;

import com.homebroker.appservice.orders.requests.OrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;

import java.util.List;

public interface OrderAppService {
    OrderResponse buyOrder(OrderRequest orderRequest);
    OrderResponse sellOrder(OrderRequest orderRequest);
    List<OrderResponse> getHistory();
}
