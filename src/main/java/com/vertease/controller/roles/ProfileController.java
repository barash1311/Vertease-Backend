package com.vertease.controller.roles;


import com.vertease.dto.register.RegisterResponse;
import com.vertease.dto.update.UpdateProfileRequest;
import com.vertease.service.roles.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<RegisterResponse> getProfile(@PathVariable String id){
        return ResponseEntity.ok(userService.getProfile(id));
    }
    @PutMapping("/me")
    public ResponseEntity<RegisterResponse> updateProfile(@PathVariable String id,@RequestBody UpdateProfileRequest request){
        return ResponseEntity.ok(userService.updateProfile(id,request));
    }
}
