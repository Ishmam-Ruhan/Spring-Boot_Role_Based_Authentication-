package com.auth.exam.Configuration;

import com.auth.exam.JWT.Filter.MyFilter;
import com.auth.exam.Services.Config.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    MyFilter myFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
          http
                  .authorizeRequests()
                  .antMatchers("/api/auth/**")
                  .permitAll()
                  .and()
                  .authorizeRequests()
                  .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                  .antMatchers("/api/user/**").hasAnyRole("USER")
                  .anyRequest()
                  .authenticated()
                  .and()
                  .exceptionHandling()
                  .and()
                  .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

          http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
