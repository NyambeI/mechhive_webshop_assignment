package com.example.Mechhive_Assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TransactionResponse {
        private List<Long> ids;
        private double PurchasePrice;
        private double tax;
        private double Total;
        private String currency;
}
