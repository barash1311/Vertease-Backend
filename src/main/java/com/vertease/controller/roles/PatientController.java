package com.vertease.controller.roles;

import com.vertease.entity.Examination;
import com.vertease.entity.MLAnalysis;
import com.vertease.service.roles.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    // NOTE: The endpoints below duplicate resource controllers. Commenting them out to
    // avoid duplicate API routes. Use these instead:
    // - /api/examinations (ExaminationController)
    // - /api/ml-analysis (MLAnalysisController)


    @GetMapping("/examinations/{patientId}")
    public ResponseEntity<List<Examination>> getExaminations(@PathVariable String patientId){
        return ResponseEntity.ok(patientService.getExaminationsByPatientId(patientId));
    }

    @GetMapping("/examinations/details/{id}")
    public ResponseEntity<Examination> getExaminationById(@PathVariable String id){
        return ResponseEntity.ok(patientService.getExaminationById(id));
    }

    @GetMapping("/ml/{examinationId}")
    public ResponseEntity<MLAnalysis> getMLAnalysis(@PathVariable String examinationId){
        return ResponseEntity.ok(patientService.getMLAnalysis(examinationId));
    }

}
