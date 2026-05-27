package app.service.wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.entity.transaction.Transaction;
import app.model.entity.transaction.TransactionStatus;
import app.model.entity.transaction.TransactionType;
import app.model.entity.user.User;
import app.model.entity.wallet.Wallet;
import app.model.entity.wallet.WalletStatus;
import app.repositiry.wallet.WalletRepository;
import app.service.transaction.TransactionService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class WalletService {
    private WalletRepository walletRepository;
    TransactionService transactionService;

    public WalletService(WalletRepository walletRepository, TransactionService transactionService) {
        this.walletRepository = walletRepository;
        this.transactionService = transactionService;
    }

    public Wallet createDefaultWallet(User owner) {
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

    // TODO transactionDTO
    public Transaction topUp(UUID walletId, BigDecimal amount) {

        Optional<Wallet> optionalWallet = walletRepository.findById(walletId);

        if (optionalWallet.isEmpty()) {
            throw new RuntimeException("Wallet not found by UUID");
        }

        Wallet wallet = optionalWallet.get();
        String transactionDescription = "Adding " + amount + " to wallet " + wallet.getId();
        if (wallet.getStatus().equals(WalletStatus.INACTIVE)) {
            return transactionService.createNewTransaction(
                    wallet.getOwner(),
                    "Angel",
                    wallet.getId().toString(),
                    amount,
                    wallet.getBalance(),
                    wallet.getCurrency(),
                    TransactionType.DEPOSIT,
                    TransactionStatus.FAILED,
                    transactionDescription,
                    "Wallet is INACTIVE");
        }

        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedOn(LocalDateTime.now());

        return transactionService.createNewTransaction(
                wallet.getOwner(),
                "Someone",
                wallet.getId().toString(),
                amount,
                wallet.getBalance(),
                wallet.getCurrency(),
                TransactionType.DEPOSIT,
                TransactionStatus.SUCCEEDED,
                transactionDescription,
                null);
    }
}
