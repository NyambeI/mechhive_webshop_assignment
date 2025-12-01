package com.example.Mechhive_Assignment.Controller;

import com.example.Mechhive_Assignment.Business.DataManager;
import com.example.Mechhive_Assignment.Domain.Product;
import com.example.Mechhive_Assignment.dto.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final DataManager dataService;

    @GetMapping
    public List<ProductResponse> getProducts(
            @RequestParam(defaultValue = "EUR") String currency)
    {
        List<Product> products = dataService.getProducts(currency);

        List<ProductResponse> result = new ArrayList<>();

        for (Product p : products) {

            DecimalFormat df = new DecimalFormat("#.##");
            double SalePrice=Double.parseDouble(df.format(p.getPrice()* 1.10));


            result.add(new ProductResponse(
                    p.getId(),
                    p.getTitle(),
                    p.getPrice(),
                    SalePrice,
                    currency
            ));
        }
        return result;
    }
}
