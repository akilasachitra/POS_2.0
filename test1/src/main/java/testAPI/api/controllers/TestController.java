package testAPI.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/doctor")
    @RolesAllowed("DOCTOR")
    Map<String, String> helloWorld() {
        return Map.of("message", "Hi DOCTOR");
    }

    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    Map<String, String> helloWorld2() {
        return Map.of("message", "Hi ADMIN");
    }

}
