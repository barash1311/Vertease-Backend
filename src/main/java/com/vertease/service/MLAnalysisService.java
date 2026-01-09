package com.vertease.service;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.entity.Examination;
import com.vertease.entity.MLAnalysis;
import com.vertease.repository.ExaminationRepository;
import com.vertease.repository.MLAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MLAnalysisService {
    private final ExaminationRepository examinationRepository;
    private final MLAnalysisRepository mlAnalysisRepository;

    public MLAnalysisResponse runMLAnalysis(String examinationId) {

        Examination examination = examinationRepository.findById(examinationId)
                .orElseThrow(() -> new RuntimeException("Examination not found"));

        // 🔹 Dummy ML logic (replace later with real ML)
        String predictedDiagnosis = "BPPV";
        Double confidenceScore = 0.87;
        String modelVersion = "v1.0";

        MLAnalysis analysis = MLAnalysis.builder()
                .examination(examination)
                .predictedDiagnosis(predictedDiagnosis)
                .confidenceScore(confidenceScore)
                .modelVersion(modelVersion)
                .build();

        MLAnalysis saved = mlAnalysisRepository.save(analysis);
        return mapToResponse(saved);
    }

    public MLAnalysisResponse getById(String id) {
        return mapToResponse(
                mlAnalysisRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ML Analysis not found"))
        );
    }

    public MLAnalysisResponse getByExaminationId(String examinationId) {
        return mapToResponse(
                mlAnalysisRepository.findByExaminationId(examinationId)
                        .orElseThrow(() -> new RuntimeException("ML Analysis not found"))
        );
    }

    private MLAnalysisResponse mapToResponse(MLAnalysis analysis) {
        MLAnalysisResponse response = new MLAnalysisResponse();
        response.setId(analysis.getId());
        response.setExaminationId(analysis.getExamination().getId());
        response.setPredictedDiagnosis(analysis.getPredictedDiagnosis());
        response.setConfidenceScore(analysis.getConfidenceScore());
        response.setModelVersion(analysis.getModelVersion());
        response.setCreatedAt(analysis.getCreatedAt());
        return response;
    }
}
