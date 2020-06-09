package com.da.assignment.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.da.assignment.exception.RecordNotFoundException;

@ControllerAdvice
public class CustomControllerAdvice {

  private static final Logger log = LoggerFactory.getLogger(CustomControllerAdvice.class);

  @ExceptionHandler({RecordNotFoundException.class})
  public final ResponseEntity<Void> beanValidation(RecordNotFoundException ex) {
    log.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .build();
  }
}
