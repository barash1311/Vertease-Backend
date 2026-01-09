package com.vertease.controller.verification;

import com.vertease.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationService verificationService;

}
