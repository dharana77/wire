package com.birely.wire.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
  private final HttpStatus httpStatus;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
