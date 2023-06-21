package com.homebroker.appservice.transaction.interfaces;

import com.homebroker.domain.ordersbook.Transaction;

import java.util.List;

public interface TransactionAppService {
    public void AddTransactions(List<Transaction> transactions);
}
