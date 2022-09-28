package daniel.springframework.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>(exception.getConstraintViolations().size());

        errors.addAll(exception.getConstraintViolations()
                .stream()
                .map(constraintViolation ->
                        constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage())
                .toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>(exception.getErrorCount());
        errors.addAll(exception.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
