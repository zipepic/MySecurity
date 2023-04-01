package zipepic.project.MySecurity.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String username;
    private String password;
}
