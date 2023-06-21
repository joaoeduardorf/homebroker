package com.homebroker.appservice.wallets.mappers;

import com.homebroker.appservice.wallets.responses.WalletResponse;
import com.homebroker.domain.wallets.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletMapper {
    public static WalletResponse toWalletResponse(Wallet wallet){
        return new WalletResponse(wallet.getWalletId(), wallet.getQuantity(), wallet.getBalance());
    }

    public static List<WalletResponse> toWalletResponse(List<Wallet> wallets){
        List<WalletResponse> walletResponses = new ArrayList<>();
        for (Wallet wallet : wallets) {
            walletResponses.add(new WalletResponse(wallet.getWalletId(), wallet.getQuantity(), wallet.getBalance()));
        }

        return walletResponses;
    }
}
