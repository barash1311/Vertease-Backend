package com.vertease.controller.admin;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.entity.MLAnalysis;
import com.vertease.entity.Verification;
import com.vertease.service.admin.AdminMLService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/ml")
@RequiredArgsConstructor
public class AdminMLController {


    private final AdminMLService adminMLService;

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

