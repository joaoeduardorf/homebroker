package com.homebroker.domain.ordersbook;

import com.homebroker.domain.orders.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {

//    private List<Order> buyOrders;
//    private List<Order> sellOrders;
    private Order order;
    private List<Order> orders;
    public OrderBook(Order order, List<Order> orders){
        this.order = order;
        this.orders = orders;
    }
    public List<Transaction> executeBuyTrade() {

        List<Transaction> transactions = new ArrayList<>();
        for (Order item : orders){
            if(order.getQuantityExecuted() != order.getQuantity() && order.getPrice() >= item.getPrice()){

                int quantity = Math.min(order.getQuantity() - order.getQuantityExecuted(), item.getQuantity() -  item.getQuantityExecuted());
                double price = item.getPrice();
                Transaction transaction = new Transaction(order.getOrderId(), item.getOrderId(), order.getWalletId(), item.getWalletId(), quantity, price);

                order.setQuantityExecuted(order.getQuantityExecuted() + quantity);
                order.setQuantity(order.getQuantity() - quantity);
                item.setQuantityExecuted(item.getQuantityExecuted() + quantity);
                item.setQuantity(order.getQuantity() - quantity);

                transactions.add(transaction);
            }
        }

        return transactions;
    }

    public List<Transaction> executeSellTrade() {

        List<Transaction> transactions = new ArrayList<>();
        for (Order item : orders){
            if(order.getQuantityExecuted() != order.getQuantity() && order.getPrice() <= item.getPrice()){

                int quantity = Math.min(order.getQuantity() - order.getQuantityExecuted(), item.getQuantity() -  item.getQuantityExecuted());
                double price = order.getPrice();
                Transaction transaction = new Transaction(order.getOrderId(), item.getOrderId(), item.getWalletId(), order.getWalletId(), quantity, price);

                order.setQuantityExecuted(order.getQuantityExecuted() + quantity);
                order.setQuantity(order.getQuantity() - quantity);
                item.setQuantityExecuted(item.getQuantityExecuted() + quantity);
                item.setQuantity(order.getQuantity() - quantity);

                transactions.add(transaction);
            }
        }

        return transactions;
    }
}
