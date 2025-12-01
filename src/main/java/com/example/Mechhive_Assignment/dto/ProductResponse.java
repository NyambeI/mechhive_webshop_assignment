package com.example.Mechhive_Assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String title;
    private double purchasePrice;
    private double salePrice;
    private String currency;

}
