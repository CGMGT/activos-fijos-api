package gt.com.tigo.fixed_assets.util;

import gt.com.tigo.fixed_assets.util.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied");
    }

}
