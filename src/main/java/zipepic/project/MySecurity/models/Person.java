package zipepic.project.MySecurity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "Person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String username;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    public Person(String name, String email, String password, String role) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
