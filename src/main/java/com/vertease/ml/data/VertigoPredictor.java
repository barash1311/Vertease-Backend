// language: java
package com.vertease.ml.data;

import com.vertease.dto.analysis.MLAnalysisResponse;
import com.vertease.dto.analysis.MLInputData;
import com.vertease.entity.enums.Diagnosis;
import weka.classifiers.Classifier;
import weka.core.Instance;

public class VertigoPredictor {

    public static MLAnalysisResponse predict(MLInputData input) {
        try {
            Classifier model = MLModelLoader.loadModel();

            Instance instance = InstanceBuilder.buildInstance(input);

            double predictionIndex = model.classifyInstance(instance);
            double[] confidence = model.distributionForInstance(instance);

            String predicted = instance.classAttribute().value((int) predictionIndex);
            Diagnosis diag = Diagnosis.valueOf(predicted);

            double conf = 0.0;
            if (confidence != null && confidence.length > (int) predictionIndex) {
                conf = confidence[(int) predictionIndex];
            }

            return createResponse(diag, conf);

        } catch (Exception e) {
            throw new RuntimeException("Prediction failed", e);
        }
    }

    private static MLAnalysisResponse createResponse(Diagnosis diag, double conf) {
        try {
            // try constructor (Diagnosis, double)
            try {
                var ctor = MLAnalysisResponse.class.getConstructor(Diagnosis.class, double.class);
                return ctor.newInstance(diag, conf);
            } catch (NoSuchMethodException ignored) {}

            // try constructor (Diagnosis, Double)
            try {
                var ctor = MLAnalysisResponse.class.getConstructor(Diagnosis.class, Double.class);
                return ctor.newInstance(diag, conf);
            } catch (NoSuchMethodException ignored) {}

            // try common static factory names
            for (String name : new String[] { "of", "from", "create", "build" }) {
                try {
                    var m = MLAnalysisResponse.class.getMethod(name, Diagnosis.class, double.class);
                    Object o = m.invoke(null, diag, conf);
                    if (o instanceof MLAnalysisResponse) return (MLAnalysisResponse) o;
                } catch (NoSuchMethodException ignored) {}
                try {
                    var m = MLAnalysisResponse.class.getMethod(name, Diagnosis.class, Double.class);
                    Object o = m.invoke(null, diag, conf);
                    if (o instanceof MLAnalysisResponse) return (MLAnalysisResponse) o;
                } catch (NoSuchMethodException ignored) {}
            }

            // fallback: default constructor + reflective field population
            MLAnalysisResponse inst = MLAnalysisResponse.class.getDeclaredConstructor().newInstance();

            String[] diagFieldNames = { "diagnosis", "label", "predictedDiagnosis", "predicted", "diagnosisByModel" };
            for (String fname : diagFieldNames) {
                try {
                    var f = MLAnalysisResponse.class.getDeclaredField(fname);
                    f.setAccessible(true);
                    if (f.getType().isEnum() || f.getType() == Diagnosis.class || f.getType().isAssignableFrom(Diagnosis.class)) {
                        f.set(inst, diag);
                    } else if (f.getType() == String.class) {
                        f.set(inst, diag.name());
                    }
                } catch (NoSuchFieldException ignored) {}
            }

            String[] confFieldNames = { "confidence", "probability", "score", "confidenceScore" };
            for (String fname : confFieldNames) {
                try {
                    var f = MLAnalysisResponse.class.getDeclaredField(fname);
                    f.setAccessible(true);
                    Class<?> t = f.getType();
                    if (t == double.class || t == Double.class) {
                        f.set(inst, conf);
                    } else if (t == float.class || t == Float.class) {
                        f.set(inst, (float) conf);
                    } else if (t == String.class) {
                        f.set(inst, Double.toString(conf));
                    }
                } catch (NoSuchFieldException ignored) {}
            }

            return inst;

        } catch (Exception e) {
            throw new RuntimeException("Failed to build MLAnalysisResponse via reflection", e);
        }
    }
}
