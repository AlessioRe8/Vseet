package it.unicam.ids.Vseet.Controller;

import it.unicam.ids.Vseet.Model.Exceptions.ContentNotFoundException;
import it.unicam.ids.Vseet.Model.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<String> handleContentNotFound() {
        return new ResponseEntity<>("Content not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound() {
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String>handleAccessDenied() {
        return new ResponseEntity<>("Access denied. You do not have permission for that", HttpStatus.FORBIDDEN);
    }

}

