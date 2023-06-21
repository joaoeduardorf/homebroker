package com.homebroker.appservice.wallets.impl;

import com.homebroker.appservice.wallets.interfaces.WalletAppService;
import com.homebroker.appservice.wallets.mappers.WalletMapper;
import com.homebroker.appservice.wallets.responses.WalletResponse;
import com.homebroker.infra.wallets.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletAppServiceImpl implements WalletAppService {
    @Autowired
    private final WalletRepository walletRepository;

    public WalletAppServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<WalletResponse> Get() {
        return WalletMapper.toWalletResponse(walletRepository.findAll());
    }

    @Override
    public WalletResponse Get(int walletId) {
        return WalletMapper.toWalletResponse(walletRepository.findById(walletId).orElse(null));
    }
}
