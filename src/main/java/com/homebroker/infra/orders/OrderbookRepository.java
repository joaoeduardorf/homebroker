package com.homebroker.infra.orders;

import com.homebroker.domain.ordersbook.OrderBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderbookRepository extends MongoRepository<OrderBook,Integer> {

}
