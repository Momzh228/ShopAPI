package edu.school21.handler;

import edu.school21.exception.DAOException;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.exception.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ResponseError> handleEntityNotFound(EntityNotFoundException ex) {
    ResponseError responseError = new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DAOException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ResponseError> handleDAOException(DAOException ex) {
    ResponseError responseError = new ResponseError(ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
