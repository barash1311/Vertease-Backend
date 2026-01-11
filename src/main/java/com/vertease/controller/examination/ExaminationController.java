
package com.vertease.controller.examination;

import com.vertease.dto.examination.ExaminationRequest;
import com.vertease.dto.examination.ExaminationResponse;
import com.vertease.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examinations")
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService examinationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PATIENT')")
    public ResponseEntity<ExaminationResponse> create(@RequestBody ExaminationRequest request) {
        return ResponseEntity.ok(examinationService.create(request));
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<List<ExaminationResponse>> getAll() {
        return ResponseEntity.ok(examinationService.getAll());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PATIENT')")
    public ResponseEntity<ExaminationResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(examinationService.getById(id));
    }
    @GetMapping("/patient")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ExaminationResponse>> getByPatient(@AuthenticationPrincipal UserDetails userDetails) {
        String patientId = userDetails.getUsername();
        return ResponseEntity.ok(examinationService.getByPatient(patientId));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<ExaminationResponse> update(@PathVariable String id, @RequestBody ExaminationRequest request) {
        return ResponseEntity.ok(examinationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        examinationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
