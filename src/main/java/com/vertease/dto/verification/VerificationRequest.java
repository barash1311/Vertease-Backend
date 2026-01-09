package com.vertease.dto.verification;

import lombok.Data;

@Data
public class VerificationRequest {
    private boolean isCorrect;
    private String correctDiagnosis;
    private String comments;
    private Integer qualityRating;
}
