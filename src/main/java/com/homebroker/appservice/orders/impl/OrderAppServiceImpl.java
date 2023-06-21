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
import com.homebroker.infra.transactions.TransactionRepository;
import com.homebroker.infra.wallets.WalletRepository;
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
        List<Order> orders = ordersRepository.findAllSellOrdersSortByPriceAndTimestamp();

        Order order = OrderMapper.ToBuyOrder(orderRequest);
        order = ordersRepository.insert(order);
        order.setTimestampQueued(Instant.now().toEpochMilli());
        ordersRepository.save(order);

        OrderBook orderBook = new OrderBook(order, orders);
        orderBook.executeTrade();
        List<Transaction> transactions = orderBook.getTransactions();

        saveTradeInformaion(transactions);

        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse sellOrder(OrderRequest orderRequest) {
        List<Order> orders = ordersRepository.findAllBuyOrdersSortByPriceAndTimestamp();

        Order order = OrderMapper.ToSellOrder(orderRequest);
        order = ordersRepository.insert(order);

        order.setTimestampQueued(Instant.now().toEpochMilli());
        ordersRepository.save(order);
        OrderBook orderBook = new OrderBook(order, orders);
        orderBook.executeTrade();
        List<Transaction> transactions = orderBook.getTransactions();

        saveTradeInformaion(transactions);

        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getHistory() {
        List<Order> orders =  ordersRepository.findAll();
        List<OrderResponse> orderResponses = OrderMapper.toOrderResponse(orders);

        return orderResponses;
    }

    void saveTradeInformaion(List<Transaction> transactions)
    {
        if(!transactions.isEmpty()){
            for (Transaction transaction : transactions) {
                Wallet buyerWallet = walletRepository.findById(transaction.getBuyerWalletId()).orElse(null);
                Wallet sellerWallet = walletRepository.findById(transaction.getSellerWalletId()).orElse(null);
                buyerWallet.buyOperation(transaction.getQuantity(), transaction.getPrice());
                sellerWallet.sellOperation(transaction.getQuantity(), transaction.getPrice());
                walletRepository.save(buyerWallet);
                walletRepository.save(sellerWallet);

                Order buyerOrder = ordersRepository.findById(transaction.getBuyerOrderId()).orElse(null);
                Order sellerOrder = ordersRepository.findById(transaction.getSellerOrderId()).orElse(null);
                buyerOrder.setQuantityExecuted(buyerOrder.getQuantityExecuted() + transaction.getQuantity());
                sellerOrder.setQuantityExecuted(sellerOrder.getQuantityExecuted() + transaction.getQuantity());
                buyerOrder.setQuantityToExecute(buyerOrder.getQuantityToExecute() - transaction.getQuantity());
                sellerOrder.setQuantityToExecute(sellerOrder.getQuantityToExecute() - transaction.getQuantity());
                ordersRepository.save(buyerOrder);
                ordersRepository.save(sellerOrder);

            }

            transactionRepository.insert(transactions);
        }
    }
}
