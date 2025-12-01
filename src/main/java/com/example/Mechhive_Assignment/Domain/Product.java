package com.example.Mechhive_Assignment.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
    public Rating rating;
}
