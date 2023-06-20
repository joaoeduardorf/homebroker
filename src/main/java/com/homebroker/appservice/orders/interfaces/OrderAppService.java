package com.homebroker.appservice.orders.interfaces;

import com.homebroker.appservice.orders.requests.OrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;
import com.homebroker.domain.orders.entities.Order;

import java.util.List;

public interface OrderAppService {
    OrderResponse AddBuyOrder(OrderRequest orderRequest);
    OrderResponse AddSellOrder(OrderRequest orderRequest);
    List<OrderResponse> GetHistory();
}
