package com.example.Mechhive_Assignment.Controller;

import com.example.Mechhive_Assignment.Business.DataManager;
import com.example.Mechhive_Assignment.Domain.Product;
import com.example.Mechhive_Assignment.dto.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class TransactionController {

    private final DataManager dataService;

    @PostMapping("/checkout")
    public TransactionResponse buyProducts(
            @RequestBody List<Long> productIds,
            @RequestParam(defaultValue = "EUR") String currency
    ) {
        List<Product> products = dataService.getProducts(currency);

        double purschasePrice = 0;

        for (Product p : products) {
            if (productIds.contains(p.getId())) {
                purschasePrice += p.getPrice();
            }
        }

        double Total = purschasePrice * 1.10;

        DecimalFormat df = new DecimalFormat("#.##");

        purschasePrice=Double.parseDouble(df.format(purschasePrice));
        Total=Double.parseDouble(df.format(Total));
        double tax = Double.parseDouble(df.format(Total - purschasePrice));

        return new TransactionResponse(
                productIds,
                purschasePrice,
                tax,
                Total,
                currency
        );
    }
}
