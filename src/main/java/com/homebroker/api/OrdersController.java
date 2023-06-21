package com.homebroker.api;

import com.homebroker.appservice.orders.interfaces.OrderAppService;
import com.homebroker.appservice.orders.requests.BuyOrderRequest;
import com.homebroker.appservice.orders.requests.SellOrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    private final OrderAppService orderAppService;

    @Autowired
    public OrdersController(OrderAppService orderAppService) {
        this.orderAppService = orderAppService;
    }

    @PostMapping("/buyorder")
    OrderResponse buyOrder(@RequestBody BuyOrderRequest buyOrderRequest){
        return orderAppService.executeOrder(buyOrderRequest);
    }

    @PostMapping("/sellorder")
    OrderResponse sellOrder(@RequestBody SellOrderRequest sellOrderRequest){
        return orderAppService.executeOrder(sellOrderRequest);
    }

    @GetMapping("/orders")
    List<OrderResponse> getHistory(){
        return orderAppService.getHistory();
    }

}
