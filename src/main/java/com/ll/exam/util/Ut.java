package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ut {
    public static class json {
        private static final ObjectMapper om;

        static {
            om = new ObjectMapper();
        }

        public static String toStr(Object obj, String defaultValue) {
            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        // 단순한 객체
        public static <T> T toObj(String jsonStr, Class<T> cls, T defaultValue) {
            try {
                return om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        // 복잡한 객체
        public static <T> T toObj(String jsonStr, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(jsonStr, typeReference);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }
    }

    public static Map<String, Object> mapOf(Object...args) {
        Map<String, Object> map = new LinkedHashMap<>();

        int dataSize = args.length / 2;

        for (int i = 0; i < dataSize; i++) {
            int keyIndex = i * 2 + 0;
            int valueIndex = i * 2 + 1;

            String Key = (String)args[keyIndex];
            Object value = args[valueIndex];

            map.put(Key, value);
        }

        return map;
    }
}