package testAPI.api.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * todo
 */

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
   // @Size(min = 6, message = "User name should have at lease minimum 6 characters")
    private String userName;

    @Email
    private String email;

   // @Size(min = 8, message = "Password should have at lease minimum 8 characters")
  //  @JsonIgnore
    private String password;
    private boolean active;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "user_id")
    private List<Role> roles;


    public User() {
    }
}
