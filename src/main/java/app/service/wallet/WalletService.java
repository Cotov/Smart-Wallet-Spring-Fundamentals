package app.service.wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.entity.user.User;
import app.model.entity.wallet.Wallet;
import app.model.entity.wallet.WalletStatus;
import app.repositiry.wallet.WalletRepository;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    public Wallet createDefaultWallet(User owner){
        System.out.println("Creating default wallet for user: " + owner.getUserName());

        LocalDateTime now = LocalDateTime.now();

        Wallet wallet = Wallet.builder()
        .owner(owner)
        .status(WalletStatus.ACTIVE)
        .balance(new BigDecimal("20.00"))
        .currency(Currency.getInstance("EUR"))
        .createdOn(now)
        .updatedOn(now)
        .build();

        System.out.println("Default wallet created");
        walletRepository.save(wallet);
        return wallet;
    }
}
