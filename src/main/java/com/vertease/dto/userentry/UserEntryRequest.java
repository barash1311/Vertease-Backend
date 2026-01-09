package com.vertease.dto.userentry;

import com.vertease.entity.enums.Sex;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntryRequest {
    @NotBlank(message = "Patient Id cant be empty")
    private String patientId;
    private String enteredBy;
    private Integer age;
    private Sex sex;
    private String comorbidities;
    private String primarySymptom;
    private String totalDuration;
    private String motionType;
    private String patternType;
    private String episodeDuration;
    private String remissionType;
    private String triggers;
    private String associatedSymptoms;
    private String earSymptoms;
    private String cerebellarSymptoms;
    private String cranialNerveSymptoms;
    private String drugHistory;
    private String postOnsetMedications;
}
