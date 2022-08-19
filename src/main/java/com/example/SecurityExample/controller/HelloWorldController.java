package com.example.SecurityExample.controller;

import com.example.SecurityExample.domain.User;
import com.example.SecurityExample.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<User> getUser(String username){
//         username = "john";
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping("/xss")
    public User getXss(User user){
//        String username = "john";

        log.info("username = " + user.getUsername());
        return user;
    }


//    @GetMapping("/create")
//    public ResponseEntity<String> createUser(){
//        userService.saveUser(new User(null, "Jongmin Han","jongmin","1234",new ArrayList<>()));
//        userService.addRoleToUser("jongmin", "ROLE_USER");
//
//        return ResponseEntity.ok().body("저장 완료");
//
//    }

}