package app.model.dto.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import app.model.entity.subscription.SubscriptionPeriod;
import app.model.entity.subscription.SubscriptionStatus;
import app.model.entity.subscription.SubscriptionType;
import app.model.entity.user.User;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SubscriptionDto {
    private UUID id;
    //todo should be dto
    private User owner;
    private SubscriptionStatus status;
    private SubscriptionPeriod period;
    private SubscriptionType type;
    private BigDecimal price;
    private boolean renewalAllowed;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private LocalDateTime completedOn;

}
