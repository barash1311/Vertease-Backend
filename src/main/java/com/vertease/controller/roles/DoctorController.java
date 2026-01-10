package com.vertease.controller.roles;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.dto.register.RegisterResponse;
import com.vertease.entity.Examination;
import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.dto.register.RegisterResponse;
import org.springframework.http.ResponseEntity;
import com.vertease.entity.Examination;
import com.vertease.service.roles.DoctorService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    // NOTE: The methods below were originally implemented in this role-based controller,
    // but they duplicate resource-based controllers and endpoints.
    // They are commented out for now to avoid having duplicate API routes.

    // - /api/examinations (ExaminationController)
    // - /api/users (UserController)
    // - /api/ml-analysis (MLAnalysisController)
    // - /api/verifications (VerificationController)


    @PostMapping("/examinations")
    public ResponseEntity<Examination> createExamination(@RequestBody Examination examination){
        return ResponseEntity.ok(doctorService.createExamination(examination));
    }

    @PutMapping("/examinations")
    public ResponseEntity<Examination> updateExamination(@PathVariable String id,@RequestBody Examination updatedExamination){
        return ResponseEntity.ok(doctorService.updateExamination(id,updatedExamination));
    }

    @GetMapping("/examinations")
    public ResponseEntity<List<Examination>> getAllExaminations(){
        return ResponseEntity.ok(doctorService.getAllExaminations());
    }

    @GetMapping("/patients")
    public ResponseEntity<List<RegisterResponse>> getAllPatients(){
        return ResponseEntity.ok(doctorService.getAllPatients());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<RegisterResponse> getPatientById(@PathVariable String id){
        return ResponseEntity.ok(doctorService.getPatientById(id));
    }

    @PostMapping("/ml/{examinationId}")
    public ResponseEntity<MLAnalysisResponse> runMLAnalysis(@PathVariable String examinationId) {
        return ResponseEntity.ok(doctorService.runMLAnalysis(examinationId));
    }


    @PostMapping("/ml/{analysisId}/verify")
    public ResponseEntity<String> verifyMLAnalysis(@PathVariable String analysisId, @RequestParam boolean isCorrect) {
        return ResponseEntity.ok(doctorService.verifyMLAnalysis(analysisId, isCorrect));
    }

}
