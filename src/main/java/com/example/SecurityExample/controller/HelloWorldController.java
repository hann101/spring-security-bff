package com.example.SecurityExample.controller;

import com.example.SecurityExample.domain.User;
import com.example.SecurityExample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    //eyJhbGciOiJIUzI1NiJ9.eyJlbXBJZCI6ImVtcDAwMSIsImVtcE5hbWUiOiJlbXBsb3llZSIsImlzcyI6IkxvZ2luIEFwcCIsImV4cCI6Im51bGwiLCJpYXQiOiIxNTUxMjMxMiJ9.X0KXPsqwq_OVF4_EF3Mca_EmWZwDRxHTfq8D-x1lIqY

    private final UserService userService;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(){
        String username = "john";
        return ResponseEntity.ok().body(userService.getUser(username));
    }

//    @GetMapping("/create")
//    public ResponseEntity<String> createUser(){
//        userService.saveUser(new User(null, "Jongmin Han","jongmin","1234",new ArrayList<>()));
//        userService.addRoleToUser("jongmin", "ROLE_USER");
//
//        return ResponseEntity.ok().body("저장 완료");
//
//    }

    @GetMapping("/logging")
    public ResponseEntity<String> logging() {
        return new ResponseEntity<>("logging/baeldung", HttpStatus.OK);
    }

}