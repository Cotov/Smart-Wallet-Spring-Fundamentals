package app.mapper.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.mapper.subscription.SubscriptionMapper;
import app.mapper.wallet.WalletMapper;
import app.model.dto.subscription.SubscriptionDto;
import app.model.dto.user.UserDto;
import app.model.dto.wallet.WalletDto;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.user.User;
import app.model.entity.user.UserRole;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // todo this needs to receive the encoded password
    public User toUser(UserRegisterRequest userRegisterRequest) {
        return userRegisterRequest == null ? null
                : User.builder()
                        .userName(userRegisterRequest.getUsername())
                        .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                        .country(userRegisterRequest.getCountry())
                        .role(UserRole.USER)
                        .isActive(true)
                        .createdOn(LocalDateTime.now())
                        .updatedOn(LocalDateTime.now())
                        .build();
    }

    public UserDto toUserDto(User user) {
        List<SubscriptionDto> subscriptionsDto = user.getSubscriptions().stream().map(SubscriptionMapper::toDto).toList();
        List<WalletDto> walletsDto = user.getWallets().stream().map(WalletMapper::toDto).toList();

        return user == null ? null
                : UserDto.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .subscriptions(subscriptionsDto)
                        .wallets(walletsDto)
                        .profilePicture(user.getProfilePicture())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .country(user.getCountry())
                        .isActive(user.isActive())
                        .createdOn(user.getCreatedOn())
                        .updatedOn(user.getUpdatedOn())
                        .build();
    }
}
