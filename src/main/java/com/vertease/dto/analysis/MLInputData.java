package com.vertease.dto.analysis;


import com.vertease.entity.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MLInputData {

    private Integer age;
    private Sex sex;

    private String primarySymptom;
    private String totalDuration;
    private String episodeDuration;
    private String motionType;
    private String patternType;
    private String triggers;

    private boolean earSymptoms;
    private boolean cerebellarSymptoms;
    private boolean cranialNerveSymptoms;


}

