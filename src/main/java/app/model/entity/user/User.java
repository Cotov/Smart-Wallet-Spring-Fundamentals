package app.model.entity.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.model.entity.subscription.Subscription;
import app.model.entity.wallet.Wallet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private Country country;


    private boolean isActive;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @OrderBy("createdOn DESC")
    private List<Subscription> subscriptions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @OrderBy("createdOn DESC")
    private List<Wallet> wallets;

}
