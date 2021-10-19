package com.auth.exam.Controllers;

import com.auth.exam.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class User {
    @Autowired
    UserService userService;

    @GetMapping("/hi")
    public String sayHi(){
        return userService.welcome();
    }
}
