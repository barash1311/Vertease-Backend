package com.vertease.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    @NotBlank(message = "Name is Required")
    private String name;
    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email Format")
    private String email;
}
