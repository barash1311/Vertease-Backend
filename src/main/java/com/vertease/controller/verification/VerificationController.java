package com.vertease.controller.verification;

import com.vertease.dto.verification.VerificationRequest;
import com.vertease.dto.verification.VerificationResponse;
import com.vertease.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/{analysisId}/verify")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<VerificationResponse> verify(
            @PathVariable String analysisId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody VerificationRequest request) {

        String userId = userDetails.getUsername();
        return ResponseEntity.ok(verificationService.verifyAnalysis(analysisId, userId, request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<List<VerificationResponse>> getAll() {
        return ResponseEntity.ok(verificationService.getAll());
    }

    @GetMapping("/analysis/{analysisId}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PATIENT')")
    public ResponseEntity<List<VerificationResponse>> getByAnalysis(
            @PathVariable String analysisId) {
        return ResponseEntity.ok(verificationService.getByAnalysis(analysisId));
    }

    @GetMapping("/correct")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<List<VerificationResponse>> getCorrect() {
        return ResponseEntity.ok(verificationService.getCorrect(true));
    }

    @GetMapping("/incorrect")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<List<VerificationResponse>> getIncorrect() {
        return ResponseEntity.ok(verificationService.getCorrect(false));
    }
}
