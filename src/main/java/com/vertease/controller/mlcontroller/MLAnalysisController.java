package com.vertease.controller.mlcontroller;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.service.MLAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ml-analysis")
@RequiredArgsConstructor
public class MLAnalysisController {
    private final MLAnalysisService mlAnalysisService;


    @PostMapping("/run/{examinationId}")
    public ResponseEntity<MLAnalysisResponse> runMLAnalysis(
            @PathVariable String examinationId) {
        return ResponseEntity.ok(mlAnalysisService.runMLAnalysis(examinationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MLAnalysisResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(mlAnalysisService.getById(id));
    }

    @GetMapping("/examination/{examinationId}")
    public ResponseEntity<MLAnalysisResponse> getByExamination(
            @PathVariable String examinationId) {
        return ResponseEntity.ok(mlAnalysisService.getByExaminationId(examinationId));
    }
}
