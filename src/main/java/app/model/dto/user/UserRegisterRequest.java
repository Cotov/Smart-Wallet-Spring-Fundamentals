package app.model.dto.user;

import app.model.entity.user.Country;
import lombok.Builder;
import lombok.Value;

@Builder
@Value

public class UserRegisterRequest {

    String username;
    String password;
    Country country;

}
