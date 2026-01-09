package com.vertease.service;

import com.vertease.dto.userentry.UserEntryRequest;
import com.vertease.dto.userentry.UserEntryResponse;
import com.vertease.entity.User;
import com.vertease.entity.UserEntry;
import com.vertease.repository.UserEntryRepository;
import com.vertease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntryService {
    private final UserRepository userRepository;
    private final UserEntryRepository userEntryRepository;

    public UserEntryResponse create(UserEntryRequest request) {
        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        UserEntry entry = UserEntry.builder()
                .patient(patient)
                .enteredBy(request.getEnteredBy())
                .age(request.getAge())
                .sex(request.getSex())
                .comorbidities(request.getComorbidities())
                .primarySymptom(request.getPrimarySymptom())
                .totalDuration(request.getTotalDuration())
                .motionType(request.getMotionType())
                .patternType(request.getPatternType())
                .episodeDuration(request.getEpisodeDuration())
                .remissionType(request.getRemissionType())
                .triggers(request.getTriggers())
                .associatedSymptoms(request.getAssociatedSymptoms())
                .earSymptoms(request.getEarSymptoms())
                .cerebellarSymptoms(request.getCerebellarSymptoms())
                .cranialNerveSymptoms(request.getCranialNerveSymptoms())
                .drugHistory(request.getDrugHistory())
                .postOnsetMedications(request.getPostOnsetMedications())
                .build();
        UserEntry createdUser=userEntryRepository.save(entry);
        return mapToResponse(createdUser);
    }

    public UserEntryResponse update(String id, UserEntryRequest request) {
        UserEntry entry = userEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        entry.setEnteredBy(request.getEnteredBy());
        entry.setAge(request.getAge());
        entry.setSex(request.getSex());
        entry.setComorbidities(request.getComorbidities());
        entry.setPrimarySymptom(request.getPrimarySymptom());
        entry.setTotalDuration(request.getTotalDuration());
        entry.setMotionType(request.getMotionType());
        entry.setPatternType(request.getPatternType());
        entry.setEpisodeDuration(request.getEpisodeDuration());
        entry.setRemissionType(request.getRemissionType());
        entry.setTriggers(request.getTriggers());
        entry.setAssociatedSymptoms(request.getAssociatedSymptoms());
        entry.setEarSymptoms(request.getEarSymptoms());
        entry.setCerebellarSymptoms(request.getCerebellarSymptoms());
        entry.setCranialNerveSymptoms(request.getCranialNerveSymptoms());
        entry.setDrugHistory(request.getDrugHistory());
        entry.setPostOnsetMedications(request.getPostOnsetMedications());

        UserEntry updatedEntry=userEntryRepository.save(entry);
        return mapToResponse(updatedEntry);
    }
    public List<UserEntryResponse> getAll() {
        return userEntryRepository.findAll().stream()
                .map(this::mapToResponse).toList();
    }

    public List<UserEntryResponse> getByPatient(String patientId) {
        return userEntryRepository.findByPatientId(patientId).stream()
                .map(this::mapToResponse).toList();
    }

    public UserEntryResponse getById(String id) {
        return mapToResponse(
                userEntryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Entry not found"))
        );
    }

        public void delete(String id) {
        userEntryRepository.deleteById(id);
    }
    private UserEntryResponse mapToResponse(UserEntry e) {
        UserEntryResponse r = new UserEntryResponse();
        r.setId(e.getId());
        r.setPatientId(e.getPatient().getId());
        r.setEnteredBy(e.getEnteredBy());
        r.setAge(e.getAge());
        r.setSex(e.getSex());
        r.setComorbidities(e.getComorbidities());
        r.setPrimarySymptom(e.getPrimarySymptom());
        r.setTotalDuration(e.getTotalDuration());
        r.setMotionType(e.getMotionType());
        r.setPatternType(e.getPatternType());
        r.setEpisodeDuration(e.getEpisodeDuration());
        r.setRemissionType(e.getRemissionType());
        r.setTriggers(e.getTriggers());
        r.setAssociatedSymptoms(e.getAssociatedSymptoms());
        r.setEarSymptoms(e.getEarSymptoms());
        r.setCerebellarSymptoms(e.getCerebellarSymptoms());
        r.setCranialNerveSymptoms(e.getCranialNerveSymptoms());
        r.setDrugHistory(e.getDrugHistory());
        r.setPostOnsetMedications(e.getPostOnsetMedications());
        r.setCreatedAt(e.getCreatedAt());
        return r;
    }
}
