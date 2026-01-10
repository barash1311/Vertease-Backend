package com.vertease.ml.data;


import com.vertease.dto.analysis.MLInputData;
import com.vertease.entity.UserEntry;
public class MLInputMapper {

    public static MLInputData fromUserEntry(UserEntry entry) {
        MLInputData input = new MLInputData();

        input.setAge(entry.getAge());
        input.setSex(entry.getSex());

        input.setPrimarySymptom(entry.getPrimarySymptom());
        input.setTotalDuration(entry.getTotalDuration());
        input.setEpisodeDuration(entry.getEpisodeDuration());
        input.setMotionType(entry.getMotionType());
        input.setPatternType(entry.getPatternType());
        input.setTriggers(entry.getTriggers());

        input.setEarSymptoms(entry.getEarSymptoms() != null);
        input.setCerebellarSymptoms(entry.getCerebellarSymptoms() != null);
        input.setCranialNerveSymptoms(entry.getCranialNerveSymptoms() != null);

        return input;
    }
}
