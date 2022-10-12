package com.example.wallet.service;

import com.example.wallet.entity.Account;
import com.example.wallet.filter.BalanceAmountZeroException;
import com.example.wallet.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccountByCifId(UUID cifId) {
        return accountRepository.findAllByCifId(cifId);
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Account updateAccountBalance(UUID accountId, BigDecimal amount, String type) {

        Account account = this.getAccountById(accountId);
        BigDecimal totalAmount;

        if (account.getBalance().doubleValue() > 0 && type.equalsIgnoreCase("in")) {

            totalAmount = account.getBalance().add(amount);

            account.setBalance(totalAmount);
            account.setLastUpdateDate(LocalDateTime.now());

        } else if (account.getBalance().doubleValue() > 0 && type.equalsIgnoreCase("out")) {

            totalAmount = account.getBalance().min(amount);
            account.setBalance(totalAmount);
            account.setLastUpdateDate(LocalDateTime.now());
        } else {
            throw new BalanceAmountZeroException("Account Balance is zero");
        }

        accountRepository.save(account);

        return account;

    }

}
