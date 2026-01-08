package com.vertease.dto.register;

import com.vertease.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private String id;
    private String name;
    private String email;
    private Role role;
    private boolean approved;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
