package com.userleonardolopez.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

public class ConversorApi {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static <T> T convertirJsonAObjeto(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            System.out.println("No es posible obtener los datos de la API. \nCausa: " + e.getMessage());
            return null;
        }
    }
}
