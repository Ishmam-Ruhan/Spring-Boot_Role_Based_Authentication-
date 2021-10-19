                                                            # Spring-Boot_Role_Based_Authentication-

**Before starting we have already User(id,name,email,ListOfRoles) and Role(id,role,ListOfUsers) class.

1. Create 'CustomUseDetails' class which implements UserDetails class
    - It has one Instance of model User class
    - create constructor and modify override methods

   @Service
2. Create 'CustomUserDetailService' which implements UserDetailsService
    - Our all database work with user happens here
        -Autowire Repo/Dao here
      @Override
    - Override one method 'loadUserbyUsername'
    - if successfully found the user by username/email/anything return as 'CustomUserDetails' object
    - else throw username not found exception
    

   @EnableWebSecurity
   @Configuration
3. Here is our main CONFIGURATION file. this file extends WebSecurityConfigurationAdapter class
     @Bean
   - This class has 4(four) beans
      i)   UserDetailsService Bean
      ii)  Password Encoder  Bean
      iii) DaoAuthenticationProvider Bean
      @Override
      iv)  AuthenticationManager Bean
     
   - This class override some methods.
      i)  configure method which takes 'AuthenticationManagerBuilder' instance as parameter
          -This class will use AuthenticationProvider and Use AuthenticationProvider Bean
      ii) configure method which takes  'HTTP' instance as parameter.
          - This class will configure our security
          - A Basic Security without Jwt and With just Http Basic and Form login given below- 
            /*---------------------------------
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
                  .formLogin()
                  .permitAll
                  
             ----------------------------*/
             
   ---------------------------------------------------------------Basic Security End---------------------------------------------------
   ------------------------------------------------------------Now We will add JWT here------------------------------------------------
   
   ** Before starting we have to create two classes: AuthRequest(email/username,password) for taking response from user & AuthResponse(token) to
   store JsonWebToken(JWT).
   
   ** After that we have to add 'jjwt' & 'jaxb-api', these two dependencies in our pom.xml file.
   
     @Service
  4. We have to create a class and place 'JWTUtill' elements here. This code will be found in our projects JWTUtils class
  
  -----------Now, we can make Json Web Token after successfully authentication-------------------
  -----To make it usable, checking it for every request we need a filter, here we use 'OncePerRequest' filter----------
  
      @Component
  5. Create a class "MyFilter" which extends 'OncePerRequest' filter.
      - It has one @Override method;
      - Implement it
     #Process
      - First, find the header from 'request'
      - Second, Create user/email and JWT string as null;
      - check the header starts with specific variable or not and header is null or not
      - If ok. then first extract token from specific variable first then, find user/email by extracting jwt token
      
      - if user/email is not null && SecurityContextHolder.getContext.getAu... == null then follow next-->
      - Using 'CustomUserDetailsService' find the user/email's UserDetails;
      - By this UserDetails object and JWT we check this JWT is valid or not. If Valid-
      - Create 'UsernamePasswordAuthenticationToken' object and inside constructor we have to pass- userDetails,null,and authorities
      - in object, we have to set details - new WebSecurity...
      - finally, SecurityContextHolder.getContext.setAu...(object)
      
 ------------Filter Set Up Complete----------------
 
 6. Finally, we have to reset the Config file http configuration
 
      http.csrf().disable();                                          // Disable CSRF
          http
                  .authorizeRequests()  
                  .antMatchers("/api/auth/**")
                  .permitAll()                                        //  Upper request will not require any authentication
                  .and()
                  .authorizeRequests()
                  .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                  .antMatchers("/api/user/**").hasAnyRole("USER")
                  .anyRequest()
                  .authenticated()                                    // Any request-authenticated means upper url's needed authentication
                  .and()
                  .exceptionHandling()
                  .and()
                  .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);   // Setting session policy stateless means no session will be created, each request needs to be authenticated

          http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);  // Finally, added our custom filter, each request will be filtered, and if authenticated okkay, you are eligible to visit this URL.
        
      
      
  
   
             
