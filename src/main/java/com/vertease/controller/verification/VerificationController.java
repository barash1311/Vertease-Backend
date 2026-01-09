package com.vertease.controller.verification;

import com.vertease.dto.verification.VerificationRequest;
import com.vertease.dto.verification.VerificationResponse;
import com.vertease.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/{analysisId}/verify/{userId}")
    public ResponseEntity<VerificationResponse> verify(
            @PathVariable String analysisId,
            @PathVariable String userId,
            @RequestBody VerificationRequest request) {

        return ResponseEntity.ok(
                verificationService.verifyAnalysis(analysisId, userId, request)
        );
    }

    @GetMapping
    public ResponseEntity<List<VerificationResponse>> getAll() {
        return ResponseEntity.ok(verificationService.getAll());
    }

    @GetMapping("/analysis/{analysisId}")
    public ResponseEntity<List<VerificationResponse>> getByAnalysis(
            @PathVariable String analysisId) {
        return ResponseEntity.ok(verificationService.getByAnalysis(analysisId));
    }

    @GetMapping("/correct")
    public ResponseEntity<List<VerificationResponse>> getCorrect() {
        return ResponseEntity.ok(verificationService.getCorrect(true));
    }

    @GetMapping("/incorrect")
    public ResponseEntity<List<VerificationResponse>> getIncorrect() {
        return ResponseEntity.ok(verificationService.getCorrect(false));
    }
}
