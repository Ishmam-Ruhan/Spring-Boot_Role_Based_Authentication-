package com.auth.exam.Services.Config;

import com.auth.exam.DAO.UserDao;
import com.auth.exam.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user= userDao.findByemail(s);

        if(user != null){
            CustomUserDetails customUserDetails=new CustomUserDetails(user);
            return customUserDetails;
        }else{
            throw new UsernameNotFoundException("User not found with email: "+s);
        }

    }
}
