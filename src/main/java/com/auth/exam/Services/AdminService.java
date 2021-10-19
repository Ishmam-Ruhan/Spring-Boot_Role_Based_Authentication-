package com.auth.exam.Services;

import com.auth.exam.DAO.RoleDao;
import com.auth.exam.DAO.UserDao;
import com.auth.exam.Models.Role;
import com.auth.exam.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String welcome(){
        return "Welcome from ADMIN service!";
    }

    public String addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return "Save User Success";
    }

    public String addRoleList(List<Role> roles){
        roleDao.saveAll(roles);
        return "Save roles Success";
    }
}
