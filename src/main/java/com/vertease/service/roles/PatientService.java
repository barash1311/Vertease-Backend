package com.vertease.service.roles;

import com.vertease.dto.register.RegisterResponse;
import com.vertease.entity.Examination;
import com.vertease.entity.MLAnalysis;
import com.vertease.entity.User;
import com.vertease.repository.ExaminationRepository;
import com.vertease.repository.MLAnalysisRepository;
import com.vertease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final UserRepository userRepository;
    private final ExaminationRepository examinationRepository;
    private final MLAnalysisRepository mlAnalysisRepository;


    public List<RegisterResponse> getAllPatients() {
        return userRepository.findAll().stream().filter(user -> user.getRole().name().equals("PATIENT")).map(this::mapToResponse).toList();
    }

    public List<Examination> getExaminationsByPatientId(String patientId) {
        return examinationRepository.findAll().stream()
                .filter(exam->
                        exam.getPatient().getId().equals((patientId)))
                .toList();
    }
    public Examination getExaminationById(String id) {
        return examinationRepository.findById(id).orElseThrow(()->new RuntimeException("Examination not found"));
    }
    public MLAnalysis getMLAnalysis(String examinationId) {
        return mlAnalysisRepository.findAll().stream()
                .filter(mlAnalysis -> mlAnalysis.getExamination().getId().equals(examinationId))
                .findFirst().orElseThrow(()-> new RuntimeException("ML analysis not found"));
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
