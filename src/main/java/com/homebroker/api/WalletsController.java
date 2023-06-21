package com.homebroker.api;

import com.homebroker.appservice.wallets.responses.WalletResponse;
import com.homebroker.appservice.wallets.interfaces.WalletAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletsController {
    @Autowired
    private final WalletAppService walletAppService;

    public WalletsController(WalletAppService walletAppService) {
        this.walletAppService = walletAppService;
    }

    @GetMapping("/wallets")
    public List<WalletResponse> get() {
        return walletAppService.Get();
    }

    @GetMapping("/wallet")
    public WalletResponse get(@RequestParam int walletId) {
        return walletAppService.Get(walletId);
    }
}
