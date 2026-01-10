package com.vertease.ml.data;

import com.vertease.dto.analysis.MLInputData;
import com.vertease.ml.data.MLFeatureSchema;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class InstanceBuilder {

    public static Instance buildInstance(MLInputData input) {

        Instances structure = new Instances(
                "VertigoPrediction",
                MLFeatureSchema.buildAttributesFromInput(),
                1
        );
        structure.setClassIndex(structure.numAttributes() - 1);

        Instance instance = new DenseInstance(structure.numAttributes());
        instance.setDataset(structure);

        // numeric
        if (input.getAge() != null) {
            instance.setValue(0, input.getAge());
        } else {
            instance.setMissing(0);
        }

        // enum / nominal
        if (input.getSex() != null) {
            instance.setValue(1, input.getSex().name());
        } else {
            instance.setMissing(1);
        }

        // string attributes (uses empty string when null to avoid missing value for string type)
        instance.setValue(2, safe(input.getPrimarySymptom()));
        instance.setValue(3, safe(input.getTotalDuration()));
        instance.setValue(4, safe(input.getEpisodeDuration()));
        instance.setValue(5, safe(input.getMotionType()));
        instance.setValue(6, safe(input.getPatternType()));
        instance.setValue(7, safe(input.getTriggers()));

        // booleans stored as nominal "false"/"true"
        instance.setValue(8, input.isEarSymptoms() ? "true" : "false");
        instance.setValue(9, input.isCerebellarSymptoms() ? "true" : "false");
        instance.setValue(10, input.isCranialNerveSymptoms() ? "true" : "false");

        return instance;
    }

    private static String safe(String v) {
        return v == null ? "" : v;
    }
}
