package com.userleonardolopez.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultadorApi {
    public static String obtenerJson(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
        } catch (RuntimeException e) {
            System.out.println("No es posible consultar la API. \nCausa: " + e.getMessage());
            return null;
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("No se obtuvo respuesta de la API. \nCausa: " + e.getMessage());
            return null;
        }
    }
}
