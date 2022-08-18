package testAPI.api.drug;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PharmacyNotFoundException extends RuntimeException {
    public PharmacyNotFoundException(String message) {
        super(message);
    }
}
