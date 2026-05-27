package app.model.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {

    @Size(min = 6, message = "Username must be at least 6 characters long")
    private String username;

    //todo pull those in constants
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

}
