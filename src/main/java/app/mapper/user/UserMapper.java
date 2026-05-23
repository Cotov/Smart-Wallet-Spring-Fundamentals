package app.mapper.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.model.dto.user.UserDto;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.user.User;
import app.model.entity.user.UserRole;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper (PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //todo this needs to receive the encoded password
    public User toUserRegisterRequest(UserRegisterRequest userRegisterRequest) {
        return User.builder()
        .userName(userRegisterRequest.getUsername())
        .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
        .country(userRegisterRequest.getCountry())
        .role(UserRole.USER)
        .isActive(true)
        .createdOn(LocalDateTime.now())
        .updatedOn(LocalDateTime.now())
        .build();
    }

    public UserDto toUserDtoMapper(User user){
        
    }

}
