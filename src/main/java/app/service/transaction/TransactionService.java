package app.service.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.springframework.stereotype.Service;

import app.model.entity.transaction.Transaction;
import app.model.entity.transaction.TransactionStatus;
import app.model.entity.transaction.TransactionType;
import app.model.entity.user.User;
import app.repositiry.transaction.TransactionRepository;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createNewTransaction(User owner, String sedner, String receiver, BigDecimal amount,
            BigDecimal balanceLeft, Currency currency,
            TransactionType type, TransactionStatus status, String description, String failureReason) {
        Transaction transaction = Transaction.builder()
                .owner(owner)
                .sender(sedner)
                .receiver(receiver)
                .amount(amount)
                .balanceLeft(balanceLeft)
                .currency(currency)
                .type(type)
                .status(status)
                .description(description)
                .failureReason(failureReason)
                .createdOn(LocalDateTime.now())
                .build();

        return transactionRepository.save(transaction);
    }
}
