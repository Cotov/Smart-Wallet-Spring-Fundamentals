package app.model.dto.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import app.model.entity.user.UserRole;
import app.model.entity.user.Country;
import app.model.dto.subscription.SubscriptionDto;
import app.model.dto.wallet.WalletDto;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserDto {

    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private UserRole role;
    private Country country;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<SubscriptionDto> subscriptions;
    private List<WalletDto> wallets;
    private boolean isActive;

}
