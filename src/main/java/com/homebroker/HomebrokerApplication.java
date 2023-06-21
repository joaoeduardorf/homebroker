package com.homebroker;

import com.homebroker.domain.wallets.Wallet;
import com.homebroker.infra.orders.OrdersRepository;
import com.homebroker.infra.transactions.TransactionRepository;
import com.homebroker.infra.wallets.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomebrokerApplication implements CommandLineRunner {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    public static void main(String[] args) {
        SpringApplication.run(HomebrokerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        walletRepository.deleteAll();
        ordersRepository.deleteAll();;
        transactionRepository.deleteAll();
        for(int i = 1; i <101; i++)
        {
            walletRepository.insert(new Wallet(i,10, 100));
        }
    }

}
