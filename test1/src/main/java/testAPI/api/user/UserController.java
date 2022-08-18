package testAPI.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    @RolesAllowed("ADMIN")
    List<User> helloWorld() {
        return userRepo.findAll();
    }


    @PostMapping("/saveUser")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Object> saveProduct(@RequestBody User user) {
        User savedUser = userRepo.save(user);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/users/{id}")
    @RolesAllowed("ADMIN")
    public User getProduct(@PathVariable Long id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent())
            return byId.get();

        throw new UserNotFoundException("User not found for id " + id);
    }


}
