package com.vertease.service.admin;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.entity.Examination;
import com.vertease.entity.MLAnalysis;
import com.vertease.entity.Verification;
import com.vertease.repository.ExaminationRepository;
import com.vertease.repository.MLAnalysisRepository;
import com.vertease.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminMLService {


        private final MLAnalysisRepository mlAnalysisRepository;
        private final ExaminationRepository examinationRepository;
        private final VerificationRepository verificationRepository;

        // Create ML analysis manually
        public MLAnalysisResponse createMLAnalysis(String examinationId) {
            Examination exam = examinationRepository.findById(examinationId)
                    .orElseThrow(() -> new RuntimeException("Examination not found"));

            MLAnalysis analysis = MLAnalysis.builder()
                    .examination(exam)
                    .predictedDiagnosis("Auto-generated diagnosis")
                    .confidenceScore(0.88)
                    .modelVersion("v1.0")
                    .build();

            MLAnalysis saved = mlAnalysisRepository.save(analysis);

            return MLAnalysisResponse.builder()
                    .id(saved.getId())
                    .examinationId(saved.getExamination().getId())
                    .predictedDiagnosis(saved.getPredictedDiagnosis())
                    .confidenceScore(saved.getConfidenceScore())
                    .modelVersion(saved.getModelVersion())
                    .createdAt(saved.getCreatedAt())
                    .updatedAt(saved.getUpdatedAt())
                    .build();
        }

        // Verify ML analysis
        public String verifyMLAnalysis(String analysisId, boolean isCorrect) {
            MLAnalysis analysis = mlAnalysisRepository.findById(analysisId)
                    .orElseThrow(() -> new RuntimeException("ML Analysis not found"));

            Verification verification = Verification.builder()
                    .mlAnalysis(analysis)
                    .isCorrect(isCorrect)
                    .verifiedAt(LocalDateTime.now())
                    .build();

            verificationRepository.save(verification);

            return isCorrect
                    ? "ML analysis verified as correct"
                    : "ML analysis marked as incorrect";
        }
}
