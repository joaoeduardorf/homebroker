package com.homebroker.infra.orders;

import com.homebroker.domain.orders.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends MongoRepository<Order, UUID> {
    @Query(value = "{ orderType : 'BUY', quantityToExecute: { $gt: 0} }", sort = "{ 'price' : -1, 'timestampRequested' : 1 }")
    List<Order> getBuyOrdersAvailable();
    @Query(value = "{ orderType: 'SELL', quantityToExecute: {$gt: 0}}", sort = "{ 'price' : 1, 'timestampRequested' : 1 }")
    List<Order> getSellOrdersAvailable();
}
