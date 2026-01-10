package com.vertease.controller.admin;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.service.admin.AdminMLService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/ml")
@RequiredArgsConstructor
public class AdminMLController {
    private final AdminMLService adminMLService;

    // NOTE: These admin-specific ML endpoints duplicate resource endpoints.
    // They are commented out to avoid duplicate mappings.
    // Prefer central endpoints:
    // - /api/ml-analysis (MLAnalysisController)
    // - /api/verifications (VerificationController)


    // Create ML analysis manually for an examination
    @PostMapping("/{examinationId}")
    public ResponseEntity<MLAnalysisResponse> createMLAnalysis(@PathVariable String examinationId) {
        return ResponseEntity.ok(adminMLService.createMLAnalysis(examinationId));
    }

    // Verify ML analysis
    @PostMapping("/{analysisId}/verify")
    public ResponseEntity<String> verifyMLAnalysis(@PathVariable String analysisId, @RequestParam boolean isCorrect) {
        return ResponseEntity.ok(adminMLService.verifyMLAnalysis(analysisId, isCorrect));
    }

}