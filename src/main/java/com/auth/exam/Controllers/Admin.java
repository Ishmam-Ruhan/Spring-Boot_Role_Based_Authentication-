package com.auth.exam.Controllers;

import com.auth.exam.Models.Role;
import com.auth.exam.Models.User;
import com.auth.exam.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class Admin {
    @Autowired
    AdminService adminService;

    @GetMapping("/hi")
    public String sayHi(){
        return adminService.welcome();
    }

    @PostMapping("/add")
    public String add(@RequestBody User user){
        return adminService.addUser(user);
    }

    @PostMapping("/addRoles")
    public String addList(@RequestBody List<Role> roles){
        return adminService.addRoleList(roles);
    }

}
