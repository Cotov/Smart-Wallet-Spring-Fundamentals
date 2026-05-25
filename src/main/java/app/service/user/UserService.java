package app.service.user;

import app.service.wallet.WalletService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.mapper.user.UserMapper;
import app.model.dto.user.UserDto;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.subscription.Subscription;
import app.model.entity.user.User;
import app.model.entity.wallet.Wallet;
import app.repositiry.user.UserRepositiry;
import app.service.subscription.SubscriptionService;

@Service
public class UserService {

    private WalletService walletService;
    private UserRepositiry userRepositiry;
    private SubscriptionService subscriptionService;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepositiry userRepositiry, UserMapper userMapper, SubscriptionService subscriptionService,
            WalletService walletService) {
        this.userRepositiry = userRepositiry;
        this.userMapper = userMapper;
        this.subscriptionService = subscriptionService;
        this.walletService = walletService;
    }

    public UserDto register(UserRegisterRequest userRegisterRequest) {

        // user
        userRepositiry.findByUserName(userRegisterRequest.getUsername()).ifPresent(user -> {
            // todo create custom exception
            throw new RuntimeException("User with this username already exists");
        });

        User userEntity = userMapper.toUser(userRegisterRequest);
        System.out.println("User saved with username: " + userEntity.getUserName());

        // subscription
        Subscription subscription = subscriptionService.createDefaultSubscription(userEntity);
        userEntity.setSubscriptions(List.of(subscription));

        // wallet
        Wallet wallet = walletService.createDefaultWallet(userEntity);
        userEntity.setWallets(List.of(wallet));

        // UserService.java:45-56 + WalletService.java:39 + SubscriptionService.java:41
        // — Wallet and subscription are saved before the owning User has an ID. With
        // default @ManyToOne (no cascade), Hibernate throws
        // TransientPropertyValueException. Save the user first, then create
        // wallet/subscription with the persisted user.
        userRepositiry.save(userEntity);

        return userMapper.toUserDto(userEntity);
    }

}
