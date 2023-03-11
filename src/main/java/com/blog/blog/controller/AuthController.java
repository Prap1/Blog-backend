package com.blog.blog.controller;

import com.blog.blog.exceptions.ApiException;
import com.blog.blog.payload.JwtAuthRequest;
import com.blog.blog.payload.JwtAuthResponse;
import com.blog.blog.payload.UserDto;
import com.blog.blog.security.JwtTokenHelper;
import com.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
   public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)throws Exception{
        this.authenticate(request.getUserName(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }
   private void authenticate(String userName,String password)throws Exception{
       UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
       try {
           this.authenticationManager.authenticate(authenticationToken);
       }catch (BadCredentialsException e){
           throw new ApiException("Invalid UserName or password");
       }
   }
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

        UserDto registerUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
    }

}
