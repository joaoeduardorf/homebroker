package com.homebroker.appservice.wallets.interfaces;

import com.homebroker.appservice.wallets.responses.WalletResponse;

import java.util.List;

public interface WalletAppService {
    List<WalletResponse> Get();
    WalletResponse Get(int walletId);

}
