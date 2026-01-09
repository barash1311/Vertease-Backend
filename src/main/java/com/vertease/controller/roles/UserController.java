package com.vertease.controller.roles;

import com.vertease.dto.register.RegisterResponse;
import com.vertease.service.roles.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RegisterResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-email")
    public ResponseEntity<RegisterResponse> getUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.checkEmail(email));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<RegisterResponse> approveDoctor(@PathVariable String id){
        return ResponseEntity.ok(userService.approveDoctor(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<RegisterResponse> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
