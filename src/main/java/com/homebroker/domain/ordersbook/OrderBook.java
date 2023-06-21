package com.homebroker.domain.ordersbook;

import com.homebroker.domain.orders.entities.Order;
import com.homebroker.domain.orders.objectvalue.OrderType;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {

//    private List<Order> buyOrders;
//    private List<Order> sellOrders;
    private Order order;
    private List<Order> orders;
    private List<Transaction> transactions;
    public OrderBook(Order order, List<Order> orders){
        this.order = order;
        this.orders = orders;
        this.transactions = new ArrayList<>();
    }
    public void executeTrade() {
        for (Order item : orders) {
            if (order.getQuantityToExecute() > 0 && buyOrSellCondition(item)) {

                int quantity = Math.min(order.getQuantity() - order.getQuantityExecuted(), item.getQuantity() - item.getQuantityExecuted());

                Transaction transaction = Transaction.createTransaction(order, item, quantity);

                updateQuantityOrderAfterTransaction(order, quantity);
                updateQuantityOrderAfterTransaction(item, quantity);

                this.transactions.add(transaction);
            } else {
                break;
            }
        }
    }

    private void updateQuantityOrderAfterTransaction(Order order, int quantity) {
        order.setQuantityExecuted(order.getQuantityExecuted() + quantity);
        order.setQuantityToExecute(order.getQuantityToExecute() - quantity);
    }

    private boolean buyOrSellCondition(Order orderByList){
        if(order.getOrderType() == OrderType.BUY){
            return order.getPrice() >= orderByList.getPrice();
        }else{
            return order.getPrice() <= orderByList.getPrice();
        }
    }


    public List<Transaction> getTransactions(){
        return transactions;
    }
}
