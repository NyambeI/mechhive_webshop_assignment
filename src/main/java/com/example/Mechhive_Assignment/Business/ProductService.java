package com.example.Mechhive_Assignment.Business;

import com.example.Mechhive_Assignment.Domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getProducts() {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fakestoreapi.com/products"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    response.body(),
                    new TypeReference<List<Product>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch products");
        }
    }

    public void SetCurrency(List<Product> products, double rate) {
        for (Product p : products) {
            DecimalFormat df = new DecimalFormat("#.##");
            double NewPrice=Double.parseDouble(df.format(p.getPrice() * rate));
            p.setPrice( NewPrice );
        }
    }
}
