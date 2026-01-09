package com.vertease.dto.examination;

import com.vertease.entity.enums.Diagnosis;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExaminationResponse {
    private String id;
    private String patientId;
    private String doctorId;
    private String clinicalFindings;
    private Diagnosis diagnosisByDoctor;
    private String notes;
    private String status;
    private LocalDateTime createdAt;
}