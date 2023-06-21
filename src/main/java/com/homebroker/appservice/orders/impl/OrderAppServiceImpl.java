package com.homebroker.appservice.orders.impl;

import com.homebroker.appservice.orders.interfaces.OrderAppService;
import com.homebroker.appservice.orders.mappers.OrderMapper;
import com.homebroker.appservice.orders.requests.OrderRequest;
import com.homebroker.appservice.orders.responses.OrderResponse;
import com.homebroker.domain.orders.entities.Order;
import com.homebroker.domain.ordersbook.OrderBook;
import com.homebroker.domain.ordersbook.Transaction;
import com.homebroker.domain.wallets.Wallet;
import com.homebroker.infra.orders.OrdersRepository;
import com.homebroker.infra.orders.TransactionRepository;
import com.homebroker.infra.orders.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderAppServiceImpl implements OrderAppService {

    private OrdersRepository ordersRepository;
    private TransactionRepository transactionRepository;
    private WalletRepository walletRepository;

    @Autowired
    public OrderAppServiceImpl(OrdersRepository ordersRepository, TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.ordersRepository = ordersRepository;
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public OrderResponse buyOrder(OrderRequest orderRequest) {
        Order order = OrderMapper.ToBuyOrder(orderRequest);
        order = ordersRepository.insert(order);
        List<Order> orders = ordersRepository.findAllSellOrdersSortByPriceAndTimestamp();
        order.setTimestampQueued(Instant.now().toEpochMilli());
        ordersRepository.save(order);
        OrderBook orderBook = new OrderBook(order, orders);
        orderBook.executeSellTrade();
        List<Transaction> transactions = orderBook.getTransactions();

        if(!transactions.isEmpty()){
            for (Transaction transaction : transactions) {
                Wallet buyerWallet = walletRepository.findById(transaction.getBuyerWalletId()).orElse(null);
                Wallet sellerWallet = walletRepository.findById(transaction.getSellerWalletId()).orElse(null);
                Order buyerOrder = ordersRepository.findById(transaction.getBuyerOrderId()).orElse(null);
                Order sellerOrder = ordersRepository.findById(transaction.getSellerOrderId()).orElse(null);
                buyerWallet.BuyOperation(transaction.getQuantity(), transaction.getPrice());
                sellerWallet.SellOperation(transaction.getQuantity(), transaction.getPrice());
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
        return OrderMapper.toOrderResponse(order);


    }

    @Override
    public OrderResponse sellOrder(OrderRequest orderRequest) {
        Order order = OrderMapper.ToSellOrder(orderRequest);
        order = ordersRepository.insert(order);
        List<Order> orders = ordersRepository.findAllBuyOrdersSortByPriceAndTimestamp();
        order.setTimestampQueued(Instant.now().toEpochMilli());
        ordersRepository.save(order);
        OrderBook orderBook = new OrderBook(order, orders);
        orderBook.executeSellTrade();
        List<Transaction> transactions = orderBook.getTransactions();

        if(!transactions.isEmpty()){
            for (Transaction transaction : transactions) {
                Wallet buyerWallet = walletRepository.findById(transaction.getBuyerWalletId()).orElse(null);
                Wallet sellerWallet = walletRepository.findById(transaction.getSellerWalletId()).orElse(null);
                Order buyerOrder = ordersRepository.findById(transaction.getBuyerOrderId()).orElse(null);
                Order sellerOrder = ordersRepository.findById(transaction.getSellerOrderId()).orElse(null);
                buyerWallet.BuyOperation(transaction.getQuantity(), transaction.getPrice());
                sellerWallet.SellOperation(transaction.getQuantity(), transaction.getPrice());
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

        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getHistory() {
        List<Order> orders =  ordersRepository.findAll();
        List<OrderResponse> orderResponses = OrderMapper.toOrderResponse(orders);

        return orderResponses;
    }
}
