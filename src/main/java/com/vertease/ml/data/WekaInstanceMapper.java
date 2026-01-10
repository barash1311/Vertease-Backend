package com.vertease.ml.data;



import com.vertease.dto.analysis.MLInputData;

import lombok.RequiredArgsConstructor;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@RequiredArgsConstructor
public class WekaInstanceMapper {
    private final MLFeatureSchema mlFeatureSchema;

    public static Instances toInstances(MLInputData input) {

        Instances dataset = new Instances(
                "VertigoPrediction",
                MLFeatureSchema.buildAttributesFromInput(),
                1
        );

        dataset.setClassIndex(dataset.numAttributes() - 1);

        Instance instance = new DenseInstance(dataset.numAttributes());
        instance.setDataset(dataset);

        instance.setValue(0, input.getAge() != null ? input.getAge() : 0);
        instance.setValue(1, input.getSex() != null ? input.getSex().name() : "MALE");

        instance.setValue(2, safe(input.getPrimarySymptom()));
        instance.setValue(3, safe(input.getTotalDuration()));
        instance.setValue(4, safe(input.getEpisodeDuration()));
        instance.setValue(5, safe(input.getMotionType()));
        instance.setValue(6, safe(input.getPatternType()));
        instance.setValue(7, safe(input.getTriggers()));

        instance.setValue(8, input.isEarSymptoms() ? 1 : 0);
        instance.setValue(9, input.isCerebellarSymptoms() ? 1 : 0);
        instance.setValue(10, input.isCranialNerveSymptoms() ? 1 : 0);

        dataset.add(instance);
        return dataset;
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }
}

