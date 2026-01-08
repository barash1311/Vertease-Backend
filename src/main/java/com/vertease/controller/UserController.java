package com.vertease.controller;

import com.vertease.dto.register.RegisterResponse;
import com.vertease.repository.UserRepository;
import com.vertease.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<RegisterResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/by-email")
    public ResponseEntity<RegisterResponse> getUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.checkEmail(email));
    }
    @PutMapping("/{id}/approve")
    public ResponseEntity<RegisterResponse> approveDoctor(@PathVariable String id){
        return ResponseEntity.ok(userService.approveDoctor(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RegisterResponse> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


}
