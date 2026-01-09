package com.vertease.dto.examination;

import com.vertease.entity.enums.Diagnosis;
import lombok.Data;

@Data
public class ExaminationRequest {
    private String patientId;
    private String doctorId;
    private String clinicalFindings;
    private Diagnosis diagnosisByDoctor;
    private String notes;
    private String status;
}
