package edu.school21.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ResponseError {

  private final int status;
  private final String message;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final Date timestamp = new Date();

  public ResponseError(String message, int status) {
    this.status = status;
    this.message = message;
  }

}
