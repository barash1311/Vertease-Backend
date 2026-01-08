package com.vertease.controller;

import com.vertease.dto.register.RegisterResponse;
import com.vertease.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<RegisterResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/by-email")
    public ResponseEntity<RegisterResponse> checkEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
