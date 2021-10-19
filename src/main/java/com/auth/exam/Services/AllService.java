package com.auth.exam.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class AllService {
    public String welcome(){
        return "Welcome from ALL service!";
    }
}
