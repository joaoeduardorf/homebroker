package com.homebroker.api;

import com.homebroker.appservice.orders.interfaces.OrderAppService;
import com.homebroker.appservice.orders.mappers.OrderMapper;
import com.homebroker.appservice.orders.requests.OrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;
import com.homebroker.domain.orders.entities.Order;
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
    OrderResponse BuyOrder(@RequestBody OrderRequest buyOrderRequest){
        return orderAppService.AddBuyOrder(buyOrderRequest);
    }

    @PostMapping("/sellorder")
    OrderResponse SellOrder(@RequestBody OrderRequest sellOrderRequest){
        return orderAppService.AddSellOrder(sellOrderRequest);
    }

    @GetMapping("/orders")
    List<OrderResponse> GetHistory(){
        return orderAppService.GetHistory();
    }

}
