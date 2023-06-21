package com.homebroker.appservice.transaction.impl;

import com.homebroker.appservice.transaction.interfaces.TransactionAppService;
import com.homebroker.domain.orders.entities.Order;
import com.homebroker.domain.ordersbook.Transaction;
import com.homebroker.domain.wallets.Wallet;
import com.homebroker.infra.orders.OrdersRepository;
import com.homebroker.infra.transactions.TransactionRepository;
import com.homebroker.infra.wallets.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionAppServiceImpl implements TransactionAppService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Override
    public void AddTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            Wallet buyerWallet = walletRepository.findById(transaction.getBuyerWalletId()).orElse(null);
            Wallet sellerWallet = walletRepository.findById(transaction.getSellerWalletId()).orElse(null);
            Order buyerOrder = ordersRepository.findById(transaction.getBuyerOrderId()).orElse(null);
            Order sellerOrder = ordersRepository.findById(transaction.getSellerOrderId()).orElse(null);
            buyerWallet.buyOperation(transaction.getQuantity(), transaction.getPrice());
            sellerWallet.sellOperation(transaction.getQuantity(), transaction.getPrice());
            buyerOrder.setQuantityExecuted(buyerOrder.getQuantityExecuted() + transaction.getQuantity());
            sellerOrder.setQuantityExecuted(sellerOrder.getQuantityExecuted() + transaction.getQuantity());
            buyerOrder.setQuantityToExecute(buyerOrder.getQuantityToExecute() - transaction.getQuantity());
            sellerOrder.setQuantityToExecute(sellerOrder.getQuantityToExecute() - transaction.getQuantity());
            ordersRepository.save(buyerOrder);
            ordersRepository.save(sellerOrder);
            walletRepository.save(buyerWallet);
            walletRepository.save(sellerWallet);
        }
        transactionRepository.insert(transactions);
    }
}
