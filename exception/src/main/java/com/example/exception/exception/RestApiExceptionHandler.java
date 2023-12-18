package com.example.exception.exception;

import com.example.exception.model.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
@Order(value = 1) //globalException보다 먼저 처리, 여기에 처리할 메소드가 없으면 글로벌로 넘어감
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {Exception.class}) //모든 컨트롤러 예외를 띄움-Global역할
    public ResponseEntity exception(Exception e){
      log.error("RestApiExceptionHandler",e);

      return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(value = {IndexOutOfBoundsException.class})    //indexOutOfException예외발생 시 띄움
    public ResponseEntity outOfBound(IndexOutOfBoundsException e){
        log.error("IndexOutOfException", e);
        return ResponseEntity.status(200).build();
    }

    //NoSuchElementException 일때만 나오는 예외처리
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<api> noSuchElement(NoSuchElementException e){
        log.error("",e);
        var res = api.builder()
                .rstCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .rstMsg(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(res);
    }


}
