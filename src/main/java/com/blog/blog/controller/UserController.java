package com.blog.blog.controller;

import com.blog.blog.payload.ApiResponse;
import com.blog.blog.payload.UserDto;
import com.blog.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   UserService userService;

    @PostMapping("/t")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){

        UserDto updatedUser=this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<?>deleteUser(@PathVariable Integer userId){
//        this.deleteUser(userId);
//        return new ResponseEntity(Map.of("message","User Deleted Sucessfully"),HttpStatus.OK);
//    }

    @PreAuthorize(("hasRole('ADMIN')"))
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable Integer userId){

        this.userService.deleteUser(userId);
    
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUsers(){

        return ResponseEntity.ok(this.userService.getALLUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUser(@PathVariable Integer userId){

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
