package com.homebroker.domain.wallets;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Wallets")
public class Wallet {

    @Id
    private int walletId;
    private int quantity;
    private double balance;
    public Wallet(int walletId, int quantity, double balance) {
        this.walletId = walletId;
        this.quantity = quantity;
        this.balance = balance;
    }

    public void buyOperation(int quantity, double value){
        this.quantity +=quantity;
        this.balance -= value * quantity;
    }

    public void sellOperation(int quantity, double value){
        this.quantity -= quantity;
        this.balance += value * quantity;
    }

    public int getWalletId() {
        return walletId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBalance() {
        return balance;
    }
}
