package com.example.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private UUID id;

    private UUID cifId;

    private String accountNumber;

    private BigDecimal balance;

    private UUID currencyId;
}
