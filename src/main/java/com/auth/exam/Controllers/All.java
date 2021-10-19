package com.auth.exam.Controllers;

import com.auth.exam.DAO.RoleDao;
import com.auth.exam.DAO.UserDao;
import com.auth.exam.Models.Role;
import com.auth.exam.Models.User;
import com.auth.exam.Services.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/all")
public class All {
    @Autowired
    AllService allService;

    @GetMapping("/hi")
    public String sayHi(){
        return allService.welcome();
    }




}
