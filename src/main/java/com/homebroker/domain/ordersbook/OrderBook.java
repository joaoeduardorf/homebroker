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
    public void executeSellTrade() {
        for (Order item : orders) {
            if (order.getQuantityToExecute() > 0 && buyOrSellCondition(item.getPrice())) {

                int quantity = Math.min(order.getQuantity() - order.getQuantityExecuted(), item.getQuantity() - item.getQuantityExecuted());
                int price = getTradePrice(item.getPrice());

                Transaction transaction = createTransaction(item, quantity, price);

                order.setQuantityExecuted(order.getQuantityExecuted() + quantity);
                order.setQuantityToExecute(order.getQuantityToExecute() - quantity);

                item.setQuantityExecuted(item.getQuantityExecuted() + quantity);
                item.setQuantityToExecute(item.getQuantityToExecute() - quantity);

                this.transactions.add(transaction);
            } else {
                break;
            }
        }
    }

    private boolean buyOrSellCondition(int price){
        if(order.getOrderType() == OrderType.BUY){
            return order.getPrice() >= price;
        }else{
            return order.getPrice() <= price;
        }
    }

    private int getTradePrice(int price){
        if(order.getOrderType() == OrderType.BUY){
            return price;
        }else{
            return order.getPrice();
        }
    }

    private Transaction createTransaction(Order orderByList, int quantity, int price) {
        if (order.getOrderType() == OrderType.BUY) {
            return new Transaction(order.getOrderId(), orderByList.getOrderId(), order.getWalletId(), orderByList.getWalletId(), quantity, price);
        } else {
            return  new Transaction(orderByList.getOrderId(), order.getOrderId(), orderByList.getWalletId(), order.getWalletId(), quantity, price);
        }
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }
}
