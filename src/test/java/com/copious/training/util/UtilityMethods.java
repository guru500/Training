package com.copious.training.util;

import com.copious.training.exceptions.GenericResponse;
import com.copious.training.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtilityMethods {


    public static <T> List<T> jsonToObject(String filePath, Class<T> type) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                (jsonElement, t,
                 jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString())).create();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();

        return gson.fromJson(reader, listType);
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> type) {

        return new Gson().fromJson(jsonString, type);
    }
}
