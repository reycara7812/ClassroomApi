package ClassroomApi.classroomApi.handler.exceptions;

import ClassroomApi.classroomApi.error.ErrorDetail;
import ClassroomApi.classroomApi.handler.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> resourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InstructorNotFoundException.class)
    public ResponseEntity<ErrorDetail> instructorNotFoundException(InstructorNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorDetail> studentNotFoundException(StudentNotFoundException e) {
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }







}
