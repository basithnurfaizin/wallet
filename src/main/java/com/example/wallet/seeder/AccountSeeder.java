package com.example.wallet.seeder;

import com.example.wallet.entity.Account;
import com.example.wallet.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class AccountSeeder {

    private final AccountRepository accountRepository;

    public AccountSeeder(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void initData() {
        List<Account> accounts = accountRepository.findAll();
        List<String> accountNumberStrings = Arrays.asList("112233","223344","556677");
        if (accounts.isEmpty()) {
            log.info("start generate data account");
            for (String accountNumber : accountNumberStrings) {
                Account account = Account.builder()
                        .id(UUID.randomUUID())
                        .accountNumber(accountNumber)
                        .cifId(UUID.randomUUID())
                        .balance(BigDecimal.valueOf(45000000000.4260))
                        .cifId(UUID.randomUUID())
                        .createdBy("SYSTEM")
                        .createdDate(LocalDateTime.now())
                        .lastUpdateBy("SYSTEM")
                        .lastUpdateDate(LocalDateTime.now())
                        .build();

                accountRepository.save(account);
            }
            log.info("finish generate data account");
        }
    }
}
