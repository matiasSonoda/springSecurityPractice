package com.app.springSecurity.controller;

import com.app.springSecurity.persistence.entity.UserEntity;
import com.app.springSecurity.persistence.entity.dto.UserEntityDto;
import com.app.springSecurity.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserEntityController {
    
    @Autowired
    private UserEntityService userEntityService;
    
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserEntityDto request){
        try{
        UserEntityDto response = userEntityService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
