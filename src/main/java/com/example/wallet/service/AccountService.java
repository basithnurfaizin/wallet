package com.example.wallet.service;

import com.example.wallet.entity.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<Account> getAllAccountByCifId(UUID cifId);
    Account getAccountById(UUID id);
    Account getAccount(String accountNumber);
    Account updateAccountBalance(UUID accountId, BigDecimal amount, String type);
}
