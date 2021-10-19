package com.auth.exam.Controllers;

import com.auth.exam.JWT.Models.AuthRequest;
import com.auth.exam.JWT.Models.JsonToken;
import com.auth.exam.JWT.Utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class Auth {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new UsernameNotFoundException("Email not found!");
        }

        UserDetails userDetails= userDetailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JsonToken(token));
    }

    @GetMapping("/hi")
    public String hello(){
        return "Hello World!";
    }

}
