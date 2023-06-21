package com.homebroker.api;

import com.homebroker.domain.ordersbook.Transaction;
import com.homebroker.infra.transactions.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController

public class TransactionsController {

    @Autowired
    private final TransactionRepository transactionRepository;

    public TransactionsController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    public List<Transaction> get() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transaction")
    public Transaction get(@RequestParam String transactionId) {
        return transactionRepository.findById(UUID.fromString(transactionId)).orElse(new Transaction());
    }
}
