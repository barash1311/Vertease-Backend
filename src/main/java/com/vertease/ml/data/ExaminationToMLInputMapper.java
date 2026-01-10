
package com.vertease.ml.data;

import com.vertease.entity.Examination;
import com.vertease.dto.analysis.MLInputData;
import com.vertease.entity.User;
import com.vertease.entity.enums.Sex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Lightweight reflection-based mapper that tries common getter names on Examination and its patient
 * to build an MLInputData. This avoids compile errors when some getters are named differently.
 * Adapt or replace with explicit mapping once your domain getters are known.
 */
public class ExaminationToMLInputMapper {

    public static MLInputData map(Examination ex) {
        MLInputData dto = new MLInputData();

        if (ex == null) {
            return dto; // all defaults / nulls / false
        }

        // Try to find a patient object on Examination
        Object patient = safeInvoke(ex, "getPatient", "patient");

        // age
        Integer age = safeInvokeToInteger(patient, "getAge", "age");
        dto.setAge(age);

        // sex (try patient then examination)
        Object sexObj = safeInvoke(patient, "getSex", "sex");
        if (sexObj == null) {
            sexObj = safeInvoke(ex, "getSex", "sex");
        }
        if (sexObj instanceof Sex) {
            dto.setSex((Sex) sexObj);
        }

        // Try common examination-level fields (primarySymptom, totalDuration, episodeDuration, motionType, patternType, triggers)
        dto.setPrimarySymptom(safeInvokeToString(ex, "getPrimarySymptom", "primarySymptom"));
        dto.setTotalDuration(safeInvokeToString(ex, "getTotalDuration", "totalDuration"));
        dto.setEpisodeDuration(safeInvokeToString(ex, "getEpisodeDuration", "episodeDuration"));
        dto.setMotionType(safeInvokeToString(ex, "getMotionType", "motionType"));
        dto.setPatternType(safeInvokeToString(ex, "getPatternType", "patternType"));
        dto.setTriggers(safeInvokeToString(ex, "getTriggers", "triggers"));

        // boolean symptom flags: try examination then patient
        dto.setEarSymptoms(safeInvokeToBoolean(ex, "getEarSymptoms", "earSymptoms", patient));
        dto.setCerebellarSymptoms(safeInvokeToBoolean(ex, "getCerebellarSymptoms", "cerebellarSymptoms", patient));
        dto.setCranialNerveSymptoms(safeInvokeToBoolean(ex, "getCranialNerveSymptoms", "cranialNerveSymptoms", patient));

        return dto;
    }

    // Helpers

    private static Object safeInvoke(Object target, String... methodOrFieldNames) {
        if (target == null) return null;
        Class<?> cls = target.getClass();
        for (String name : methodOrFieldNames) {
            // try method getX / x
            String[] candidates = new String[] { "get" + capitalize(name), name };
            for (String m : candidates) {
                try {
                    Method method = cls.getMethod(m);
                    return method.invoke(target);
                } catch (NoSuchMethodException ignored) {
                } catch (Exception ignored) {
                }
            }
            // try direct field
            try {
                Field f = cls.getDeclaredField(name);
                f.setAccessible(true);
                return f.get(target);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private static Integer safeInvokeToInteger(Object target, String... names) {
        Object o = safeInvoke(target, names);
        if (o instanceof Number) {
            return ((Number) o).intValue();
        }
        try {
            return o != null ? Integer.valueOf(o.toString()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    private static String safeInvokeToString(Object target, String... names) {
        Object o = safeInvoke(target, names);
        return o != null ? o.toString() : null;
    }

    private static boolean safeInvokeToBoolean(Object primary, String name, String altName, Object fallbackTarget) {
        Object o = safeInvoke(primary, name, altName);
        if (o == null && fallbackTarget != null) {
            o = safeInvoke(fallbackTarget, name, altName);
        }
        if (o instanceof Boolean) {
            return (Boolean) o;
        }
        if (o != null) {
            String s = o.toString().toLowerCase();
            return s.equals("true") || s.equals("1") || s.equals("yes");
        }
        return false;
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
