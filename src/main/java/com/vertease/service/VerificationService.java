package com.vertease.service;

import com.vertease.dto.verification.VerificationRequest;
import com.vertease.dto.verification.VerificationResponse;
import com.vertease.entity.MLAnalysis;
import com.vertease.entity.User;
import com.vertease.entity.Verification;
import com.vertease.repository.MLAnalysisRepository;
import com.vertease.repository.UserRepository;
import com.vertease.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationRepository verificationRepository;
    private final MLAnalysisRepository mlAnalysisRepository;
    private final UserRepository userRepository;

    public VerificationResponse verifyAnalysis(
            String analysisId,
            String verifierUserId,
            VerificationRequest request) {

        if (verificationRepository.existsByMlAnalysisId(analysisId)) {
            throw new RuntimeException("This ML analysis is already verified");
        }

        MLAnalysis analysis = mlAnalysisRepository.findById(analysisId)
                .orElseThrow(() -> new RuntimeException("ML Analysis not found"));

        User verifier = userRepository.findById(verifierUserId)
                .orElseThrow(() -> new RuntimeException("Verifier not found"));

        if (!(verifier.getRole().name().equals("DOCTOR")
                || verifier.getRole().name().equals("ADMIN"))) {
            throw new RuntimeException("Only doctors or admins can verify ML analysis");
        }
        Verification verification = Verification.builder()
                .mlAnalysis(analysis)
                .verifiedBy(verifier)
                .isCorrect(request.isCorrect())
                .correctDiagnosis(request.getCorrectDiagnosis())
                .comments(request.getComments())
                .qualityRating(request.getQualityRating())
                .build();

        return mapToResponse(verificationRepository.save(verification));
    }

    public List<VerificationResponse> getAll() {
        return verificationRepository.findAll()
                .stream().map(this::mapToResponse).toList();
    }

    public List<VerificationResponse> getByAnalysis(String analysisId) {
        return verificationRepository.findByMlAnalysisId(analysisId)
                .stream().map(this::mapToResponse).toList();
    }

    public List<VerificationResponse> getCorrect(boolean correct) {
        return verificationRepository.findByIsCorrect(correct)
                .stream().map(this::mapToResponse).toList();
    }

    private VerificationResponse mapToResponse(Verification v) {
        VerificationResponse r = new VerificationResponse();
        r.setId(v.getId());
        r.setMlAnalysisId(v.getMlAnalysis().getId());
        r.setVerifiedByUserId(v.getVerifiedBy().getId());
        r.setCorrect(v.isCorrect());
        r.setCorrectDiagnosis(v.getCorrectDiagnosis());
        r.setComments(v.getComments());
        r.setQualityRating(v.getQualityRating());
        r.setVerifiedAt(v.getVerifiedAt());
        return r;
    }
}
