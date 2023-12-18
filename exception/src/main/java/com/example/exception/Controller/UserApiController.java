package com.example.exception.Controller;

import com.example.exception.model.User;
import com.example.exception.model.api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private static List<User> userList = List.of(
            User.builder()
                    .id("1")
                    .name("홍길동")
                    .age(10)
                    .build()
            ,
            User.builder()
                    .id("2")
                    .name("유관순")
                    .age(12)
                    .build()
    );


    @GetMapping("/id/{userId}")
    public api<User> getUser(
            @PathVariable String userId
    ){
        if(true) {
            throw new RuntimeException(("message"));
        }

        var user = userList.stream()
                .filter(
                        it -> it.getId().equals(userId)
                )
                .findFirst()
                .get();

        api<User> res = api.<User>builder()
                .rstCode(String.valueOf(HttpStatus.OK.value()))
                .rstMsg(HttpStatus.OK.name())
                .data(user)
                .build();
        return res;

        //http://localhost:8080/api/user/id/2

        /*{
            "rst_code": "200",
            "rst_msg": "OK",
            "data":{
            "name": "유관순",
            "age": 12,
            "id": "2"
            }
        }*/

    }
}
