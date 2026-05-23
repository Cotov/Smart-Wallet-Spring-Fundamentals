package app.repositiry.wallet;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.entity.wallet.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

}
