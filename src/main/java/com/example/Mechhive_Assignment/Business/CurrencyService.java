package com.example.Mechhive_Assignment.Business;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class CurrencyService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Double> getRates() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/eur.json"))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, Object> full = mapper.readValue(response.body(), new TypeReference<>() {});

            return (Map<String, Double>) full.get("eur");

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch rates", e);
        }
    }

}
