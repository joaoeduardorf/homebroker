package com.homebroker.infra.transactions;

import com.homebroker.domain.ordersbook.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TransactionRepository extends MongoRepository<Transaction, UUID> {
}
