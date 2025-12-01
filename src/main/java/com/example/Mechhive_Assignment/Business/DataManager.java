package com.example.Mechhive_Assignment.Business;

import com.example.Mechhive_Assignment.Domain.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DataManager {

    private static final String PRODUCTS_KEY = "products";
    private static final String RATES_KEY = "rates";

    private final ProductService productService;
    private final CurrencyService currencyService;
    private final RedisTemplate<String, Object> redis;

    @PostConstruct
    public void init() {
        refreshProducts(productService.getProducts());
        refreshRates(currencyService.getRates());
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void refresh() {
        refreshProducts(productService.getProducts());
        refreshRates(currencyService.getRates());
    }

    private void refreshProducts(List<Product> products) {
        redis.opsForValue().set(PRODUCTS_KEY, products);
    }

    private void refreshRates(Map<String, Double> rates) {
        redis.opsForValue().set(RATES_KEY, rates);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProducts(String currency) {

        List<Product> products= (List<Product>) redis.opsForValue().get(PRODUCTS_KEY);
        Map<String, Double> rates = getRates();

        if (!currency.equalsIgnoreCase("eur")) {
            double rate = rates.get(currency.toLowerCase());
            productService.SetCurrency(products, rate);
        }

        return  products;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Double> getRates() {
        return (Map<String, Double>) redis.opsForValue().get(RATES_KEY);
    }
}
