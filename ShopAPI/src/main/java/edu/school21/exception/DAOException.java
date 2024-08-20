package edu.school21.exception;

import lombok.Getter;

@Getter
public class DAOException extends RuntimeException {

  public DAOException(String message) {
    super(message);
  }

}
