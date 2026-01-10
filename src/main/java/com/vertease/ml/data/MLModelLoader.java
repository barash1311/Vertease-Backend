package com.vertease.ml.data;

import weka.classifiers.Classifier;
import weka.core.SerializationHelper;

public class MLModelLoader {
    private static final String MODEL_PATH = "ml/model/vertigo.model";

    public static Classifier loadModel() {
        try {
            return (Classifier) SerializationHelper.read(MODEL_PATH);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load ML model", e);
        }
    }
}
