// language: java
// File: src/main/java/com/vertease/ml/data/TrainingDataBuilder.java
package com.vertease.ml.data;

import com.vertease.entity.Verification;
import com.vertease.dto.analysis.MLInputData;
import com.vertease.entity.Examination;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.List;

public class TrainingDataBuilder {
    public Instances buildTrainingSet(List<Verification> verifiedData) {

        Instances dataset = new Instances(
                "VertigoTrainingData",
                MLFeatureSchema.buildAttributesFromInput(),
                Math.max(verifiedData.size(), 1)
        );

        dataset.setClassIndex(dataset.numAttributes() - 1);

        for (Verification v : verifiedData) {
            Instance instance = new DenseInstance(dataset.numAttributes());
            instance.setDataset(dataset);

            // obtain Examination safely
            Examination ex = null;
            if (v.getMlAnalysis() != null) {
                ex = v.getMlAnalysis().getExamination();
            }

            MLInputData entry = ExaminationToMLInputMapper.map(ex);

            // numeric / basic fields (use safe null-handling)
            if (entry.getAge() != null) {
                instance.setValue(0, entry.getAge());
            } else {
                instance.setMissing(0);
            }

            instance.setValue(1, entry.getSex() != null ? entry.getSex().name() : "UNKNOWN");
            instance.setValue(2, safe(entry.getPrimarySymptom()));
            instance.setValue(3, safe(entry.getTotalDuration()));
            instance.setValue(4, safe(entry.getEpisodeDuration()));
            instance.setValue(5, safe(entry.getMotionType()));
            instance.setValue(6, safe(entry.getPatternType()));
            instance.setValue(7, safe(entry.getTriggers()));

            // booleans: set as nominal string "true"/"false" (matches MLFeatureSchema boolean handling)
            instance.setValue(8, entry.isEarSymptoms() ? "true" : "false");
            instance.setValue(9, entry.isCerebellarSymptoms() ? "true" : "false");
            instance.setValue(10, entry.isCranialNerveSymptoms() ? "true" : "false");

            // Label - keep original call; adjust if your Verification uses a different method
            instance.setClassValue(v.getCorrectDiagnosis());

            dataset.add(instance);
        }

        return dataset;
    }

    private static String safe(String v) {
        return v == null ? "" : v;
    }
}
