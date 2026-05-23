package app.repositiry.subscription;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.entity.subscription.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

}
