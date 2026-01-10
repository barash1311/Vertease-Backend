package com.vertease.service.roles;

import com.vertease.dto.login.LoginRequest;
import com.vertease.dto.login.LoginResponse;
import com.vertease.dto.register.RegisterRequest;
import com.vertease.dto.register.RegisterResponse;
import com.vertease.dto.update.UpdateProfileRequest;
import com.vertease.entity.User;
import com.vertease.entity.enums.Role;
import com.vertease.repository.UserRepository;
import com.vertease.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;


    public RegisterResponse register(RegisterRequest registerRequest) {
        Role role=registerRequest.getRole()!=null?registerRequest.getRole():Role.PATIENT;
        User user=User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(role)
                .build();
        User savedUser=userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private RegisterResponse mapToResponse(User savedUser) {
        RegisterResponse response=new RegisterResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setApproved(savedUser.isApproved());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(loginRequest.getPassword()==null){
            throw new RuntimeException("Invalid Credentials");
        }
        String accessToken = jwtUtils.generateAccessToken(user.getEmail(), user.getRole().name());
        String refreshToken = jwtUtils.generateRefreshToken(user.getEmail(), user.getRole().name());
        return new LoginResponse(accessToken, refreshToken, mapToResponse(user));
    }

    public List<RegisterResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public RegisterResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public RegisterResponse approveDoctor(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setApproved(true);
        userRepository.save(user);
        return mapToResponse(user);
    }

    public void deleteUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public RegisterResponse getProfile(String id) {
        User user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        return mapToResponse(user);

    }

    public RegisterResponse updateProfile(String id, UpdateProfileRequest updatedUser) {
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        userRepository.save(user);
        return mapToResponse(user);

    }
}
