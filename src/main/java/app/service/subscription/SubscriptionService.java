package app.service.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.entity.subscription.Subscription;
import app.model.entity.subscription.SubscriptionPeriod;
import app.model.entity.subscription.SubscriptionStatus;
import app.model.entity.subscription.SubscriptionType;
import app.model.entity.user.User;
import app.repositiry.subscription.SubscriptionRepository;

@Service
@Transactional
public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createDefaultSubscription(User user) {
        
        LocalDateTime now = LocalDateTime.now();

        Subscription subscription = Subscription.builder()
        .owner(user)
        .status(SubscriptionStatus.ACTIVE)
        .type(SubscriptionType.DEFAULT)
        .price(new BigDecimal("0.00"))
        .renewalAllowed(true)
        .createdOn(now)
        .updatedOn(now)
        .comlpetedOn(now.plusMonths(1))
        .period(SubscriptionPeriod.MONTHLY)
        .build();

        System.out.println("Subscription created for user " + user.getUserName());

        subscriptionRepository.save(subscription);

        return subscription;
    }

}
