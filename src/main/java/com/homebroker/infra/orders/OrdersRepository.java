package com.homebroker.infra.orders;

import com.homebroker.domain.orders.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends MongoRepository<Order, UUID> {
    @Query(value = "{ orderType : 'BUY' }", sort = "{ 'price' : -1, 'timestampRequested' : -1 }")
    List<Order> findAllBuyOrdersSortByPriceAndTimestamp();
    @Query(value = "{ orderType: 'SELL'}", sort = "{ 'price' : 1, 'timestampRequested' : -1 }")
    List<Order> findAllSellOrdersSortByPriceAndTimestamp();
}
