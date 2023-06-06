package uz.pdp.telegraphapi_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoResultsExceptionController {
    @ExceptionHandler(value = UserInfNotTrueException.class)
    public ResponseEntity<Object> exception(UserInfNotTrueException exception) {
        return new ResponseEntity<>("Username or password is blank", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> ex(UserNotFoundException e) {
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }
}
