package com.vertease.service;

import com.vertease.dto.examination.ExaminationRequest;
import com.vertease.dto.examination.ExaminationResponse;
import com.vertease.entity.Examination;
import com.vertease.entity.User;
import com.vertease.repository.ExaminationRepository;
import com.vertease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final UserRepository userRepository;

    public ExaminationResponse create(ExaminationRequest request) {
        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Examination exam = new Examination();
        exam.setPatient(patient);
        exam.setDoctor(doctor);
        exam.setClinicalFindings(request.getClinicalFindings());
        exam.setDiagnosisByDoctor(request.getDiagnosisByDoctor());
        exam.setNotes(request.getNotes());
        exam.setStatus(request.getStatus());

        return mapToResponse(examinationRepository.save(exam));
    }

    public List<ExaminationResponse> getAll() {
        return examinationRepository.findAll()
                .stream().map(this::mapToResponse).toList();
    }

    public ExaminationResponse getById(String id) {
        return mapToResponse(
                examinationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Examination not found"))
        );
    }

    public List<ExaminationResponse> getByPatient(String patientId) {
        return examinationRepository.findByPatientId(patientId)
                .stream().map(this::mapToResponse).toList();
    }

    public ExaminationResponse update(String id, ExaminationRequest request) {
        Examination exam = examinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination not found"));

        exam.setClinicalFindings(request.getClinicalFindings());
        exam.setDiagnosisByDoctor(request.getDiagnosisByDoctor());
        exam.setNotes(request.getNotes());
        exam.setStatus(request.getStatus());

        return mapToResponse(examinationRepository.save(exam));
    }

    public void delete(String id) {
        examinationRepository.deleteById(id);
    }

    private ExaminationResponse mapToResponse(Examination e) {
        ExaminationResponse r = new ExaminationResponse();
        r.setId(e.getId());
        r.setPatientId(e.getPatient().getId());
        r.setDoctorId(e.getDoctor().getId());
        r.setClinicalFindings(e.getClinicalFindings());
        r.setDiagnosisByDoctor(e.getDiagnosisByDoctor());
        r.setNotes(e.getNotes());
        r.setStatus(e.getStatus());
        r.setCreatedAt(e.getCreatedAt());
        return r;
    }
}

