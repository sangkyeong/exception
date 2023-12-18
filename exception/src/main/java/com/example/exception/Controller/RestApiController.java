package com.example.exception.Controller;

import com.example.exception.model.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "")
    public void hello(){
        throw new RuntimeException("Run time Exception Call!!");
    }
    

}
