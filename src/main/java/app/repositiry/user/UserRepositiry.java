package app.repositiry.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.entity.user.User;

@Repository
public interface UserRepositiry extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);

}
