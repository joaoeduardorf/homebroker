package com.homebroker.appservice.wallets.responses;

public class WalletResponse {
    private int walletId;
    private int quantity;
    private double balance;
    public WalletResponse(int walletId, int quantity, double balance) {
        this.walletId = walletId;
        this.quantity = quantity;
        this.balance = balance;
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
