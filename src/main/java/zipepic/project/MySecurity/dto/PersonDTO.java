package zipepic.project.MySecurity.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String username;
    @Email
    private String email;

    private String password;
    //TODO
}
