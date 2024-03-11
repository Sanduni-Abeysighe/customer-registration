package samplejsp.example.demo.domain.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

    public CustomException(String reason, HttpStatus status) {
        super(status, reason);
    }
}
