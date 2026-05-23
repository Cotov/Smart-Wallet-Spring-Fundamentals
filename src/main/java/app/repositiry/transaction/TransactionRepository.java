package app.repositiry.transaction;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.entity.transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
