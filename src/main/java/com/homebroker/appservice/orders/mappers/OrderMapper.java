package com.homebroker.appservice.orders.mappers;

import com.homebroker.appservice.orders.requests.OrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;
import com.homebroker.domain.orders.entities.Order;
import com.homebroker.domain.orders.objectvalue.OrderStatus;
import com.homebroker.domain.orders.objectvalue.OrderType;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order ToBuyOrder(OrderRequest orderRequest){
        return new Order(orderRequest.walletId ,orderRequest.quantity, orderRequest.price, OrderType.BUY, OrderStatus.REQUESTED);
    }

    public static Order ToSellOrder(OrderRequest orderRequest){
        return new Order(orderRequest.walletId, orderRequest.quantity, orderRequest.price, OrderType.SELL, OrderStatus.REQUESTED);
    }

    public static OrderResponse toOrderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse(order.getOrderId(), order.getWalletId(), order.getQuantity(), order.getPrice(),order.getOrderType(), order.getOrderStatus(),order.getTimestampRequested());
        return orderResponse;
    }

    public static List<OrderResponse> toOrderResponse(List<Order> orders){
        List<OrderResponse> orderResponses = new ArrayList<>();
        if(orders != null) {
            for (Order order : orders) {
                orderResponses.add(new OrderResponse(order.getOrderId(), order.getWalletId(), order.getQuantity(), order.getPrice(), order.getOrderType(), order.getOrderStatus(), order.getTimestampRequested()));
            }
        }
        return orderResponses;
    }
}
