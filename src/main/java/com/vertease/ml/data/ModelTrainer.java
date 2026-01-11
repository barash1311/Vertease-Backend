package com.vertease.ml.data;


import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ModelTrainer {

    public static void trainAndSave(Instances trainingData) {

        try {
            RandomForest model = new RandomForest();
            model.setNumIterations(100);
            model.buildClassifier(trainingData);

            SerializationHelper.write(
                    "ml/model/vertigo.model",
                    model
            );

        } catch (Exception e) {
            throw new RuntimeException("Model training failed", e);
        }
    }
}

