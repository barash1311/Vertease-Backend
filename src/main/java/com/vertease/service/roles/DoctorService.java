package com.vertease.service.roles;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.dto.register.RegisterResponse;
import com.vertease.entity.Examination;
import com.vertease.entity.MLAnalysis;
import com.vertease.entity.User;
import com.vertease.entity.Verification;
import com.vertease.entity.enums.Diagnosis;
import com.vertease.repository.ExaminationRepository;
import com.vertease.repository.MLAnalysisRepository;
import com.vertease.repository.UserRepository;
import com.vertease.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final UserRepository userRepository;
    private final ExaminationRepository examinationRepository;
    private final MLAnalysisRepository mlAnalysisRepository;
    private final VerificationRepository verificationRepository;

    public Examination createExamination(Examination examination) {
        return examinationRepository.save(examination);
    }

    public Examination updateExamination(String id, Examination updatedExamination) {
        Examination examination=examinationRepository.findById(id).orElseThrow(()-> new RuntimeException("Examination not found"));
        examination.setClinicalFindings(updatedExamination.getClinicalFindings());
        examination.setDiagnosisByDoctor(updatedExamination.getDiagnosisByDoctor());
        examination.setNotes(updatedExamination.getNotes());
        examination.setStatus(updatedExamination.getStatus());
        return examinationRepository.save(examination);
    }

    public List<Examination> getAllExaminations() {
        return examinationRepository.findAll();
    }

    public List<RegisterResponse> getAllPatients() {
        return userRepository.findAll().stream().filter(u->u.getRole().name().equals("PATIENT")).map(this::mapToResponse).toList();
    }

    public RegisterResponse getPatientById(String id) {
        User user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found!"));
        return mapToResponse(user);
    }

    public MLAnalysisResponse runMLAnalysis(String examinationId) {
        Examination examination=examinationRepository.findById(examinationId).orElseThrow(()-> new RuntimeException("Examination not found!"));
        Diagnosis predictedDiagnosis = Diagnosis.BPPV;
        Double confidenceScore = 0.82;
        String modelVersion = "v1.0";
        MLAnalysis analysis=MLAnalysis.builder()
                .examination(examination)
                .predictedDiagnosis(predictedDiagnosis.name())
                .confidenceScore(confidenceScore)
                .modelVersion(modelVersion)
                .build();
        MLAnalysis saved=mlAnalysisRepository.save(analysis);
        return mapToMLAnalysisResponse(saved);
    }

    public String verifyMLAnalysis(String analysisId, boolean isCorrect) {
        MLAnalysis analysis = mlAnalysisRepository.findById(analysisId)
                .orElseThrow(() -> new RuntimeException("ML Analysis not found"));

        // Create a new Verification record
        Verification verification = Verification.builder()
                .mlAnalysis(analysis)
                .isCorrect(isCorrect)
                .verifiedAt(LocalDateTime.now())
                .build();

        verificationRepository.save(verification);

        return isCorrect ? "correct" : "incorrect";
    }

    private MLAnalysisResponse mapToMLAnalysisResponse(MLAnalysis analysis) {
        return MLAnalysisResponse.builder()
                .id(analysis.getId())
                .examinationId(analysis.getExamination().getId())
                .predictedDiagnosis(analysis.getPredictedDiagnosis())
                .confidenceScore(analysis.getConfidenceScore())
                .modelVersion(analysis.getModelVersion())
                .createdAt(analysis.getCreatedAt())
                .updatedAt(analysis.getUpdatedAt())
                .build();
    }
    private RegisterResponse mapToResponse(User savedUser) {
        RegisterResponse response=new RegisterResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setApproved(savedUser.isApproved());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    }

}
