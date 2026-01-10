package com.vertease.ml.data;

import com.vertease.dto.analysis.MLInputData;
import com.vertease.entity.enums.Diagnosis;
import lombok.RequiredArgsConstructor;
import weka.core.Attribute;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MLFeatureSchema {

    public static ArrayList<Attribute> buildAttributesFromInput() {
        ArrayList<Attribute> attributes = new ArrayList<>();

        Field[] fields = MLInputData.class.getDeclaredFields();
        for (Field f : fields) {
            // skip synthetic or static fields (e.g., serialVersionUID)
            if (f.isSynthetic() || Modifier.isStatic(f.getModifiers())) {
                continue;
            }

            String name = f.getName();
            Class<?> type = f.getType();

            if (isNumericType(type)) {
                attributes.add(new Attribute(name)); // numeric
            } else if (type.isEnum()) {
                List<String> values = Arrays.stream(type.getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.toList());
                attributes.add(new Attribute(name, values)); // nominal from enum
            } else if (type == boolean.class || type == Boolean.class) {
                attributes.add(new Attribute(name, Arrays.asList("false", "true"))); // boolean nominal
            } else {
                @SuppressWarnings("unchecked")
                List<String> stringMeta = (List<String>) null;
                attributes.add(new Attribute(name, stringMeta)); // string attribute
            }
        }

        // Append class label (diagnosis) built from Diagnosis enum
        List<String> diagnosisValues = Arrays.stream(Diagnosis.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        attributes.add(new Attribute("diagnosis", diagnosisValues));

        return attributes;
    }

    private static boolean isNumericType(Class<?> type) {
        return Number.class.isAssignableFrom(type)
                || type == int.class || type == long.class
                || type == double.class || type == float.class
                || type == short.class || type == byte.class
                || type == Integer.class || type == Long.class
                || type == Double.class || type == Float.class
                || type == Short.class || type == Byte.class;
    }
}
