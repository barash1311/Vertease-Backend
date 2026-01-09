package com.vertease.dto.verification;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VerificationResponse {
    private String id;
    private String mlAnalysisId;
    private String verifiedByUserId;
    private boolean isCorrect;
    private String correctDiagnosis;
    private String comments;
    private Integer qualityRating;
    private LocalDateTime verifiedAt;
}