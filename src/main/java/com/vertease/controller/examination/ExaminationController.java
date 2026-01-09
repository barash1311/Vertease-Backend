package com.vertease.controller.examination;
import com.vertease.dto.examination.ExaminationRequest;
import com.vertease.dto.examination.ExaminationResponse;
import com.vertease.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examinations")
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService examinationService;

    @PostMapping
    public ResponseEntity<ExaminationResponse> create(
            @RequestBody ExaminationRequest request) {
        return ResponseEntity.ok(examinationService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ExaminationResponse>> getAll() {
        return ResponseEntity.ok(examinationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(examinationService.getById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ExaminationResponse>> getByPatient(
            @PathVariable String patientId) {
        return ResponseEntity.ok(examinationService.getByPatient(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminationResponse> update(
            @PathVariable String id,
            @RequestBody ExaminationRequest request) {
        return ResponseEntity.ok(examinationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        examinationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

