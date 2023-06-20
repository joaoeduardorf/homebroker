package com.homebroker.infra.orders;

import com.homebroker.domain.wallets.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet,Integer> {
}
