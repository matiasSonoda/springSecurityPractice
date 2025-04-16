
package com.app.springSecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {
    
    
    
    @GetMapping("/get")
    public String helloGet(){
        return "hello world - GET";
    }
    
    @PatchMapping("/patch")
    public String helloRefactor(){
        return "hello world - REFACTOR";
    }
    
}
