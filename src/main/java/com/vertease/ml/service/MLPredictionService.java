package com.vertease.ml.service;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.dto.analysis.MLInputData;
import com.vertease.entity.enums.Diagnosis;
import com.vertease.ml.data.MLModelLoader;
import com.vertease.ml.data.WekaInstanceMapper;
import weka.classifiers.Classifier;
import weka.core.Instances;

public class MLPredictionService {
    private static final Classifier model = MLModelLoader.loadModel();

    public static MLAnalysisResponse predict(MLInputData input) {

        try {
            Instances data = WekaInstanceMapper.toInstances(input);
            double predictionIndex = model.classifyInstance(data.instance(0));
            double confidence = model.distributionForInstance(data.instance(0))[(int) predictionIndex];

            String diagnosisLabel = data.classAttribute().value((int) predictionIndex);

            return MLAnalysisResponse.builder()
                    .predictedDiagnosis(diagnosisLabel)
                    .confidenceScore(confidence)
                    .modelVersion("v1.0")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("ML Prediction failed", e);
        }
    }
}
