package testAPI.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {


    private final UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    @RolesAllowed("ROLE_USER")
    List<User> helloWorld() {
        return userRepo.findAll();
    }


    @PostMapping("/saveUser")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> saveProduct(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(user);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri())
                .build();
    }

    @DeleteMapping("/users/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public void deleteByID(@PathVariable long id) {
        userRepo.deleteById(id);
    }

    @GetMapping("/users/{id}")
    @RolesAllowed("ROLE_USER")
    public User getProduct(@PathVariable Long id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent())
            return byId.get();

        throw new UserNotFoundException("User not found for id " + id);
    }

}
