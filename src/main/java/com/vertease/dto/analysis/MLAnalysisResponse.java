package com.vertease.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MLAnalysisResponse {
    private String id;
    private String examinationId;
    private String predictedDiagnosis;
    private Double confidenceScore;
    private String modelVersion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
