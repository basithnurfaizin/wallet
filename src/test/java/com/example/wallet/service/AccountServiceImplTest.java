package com.example.wallet.service;

import com.example.wallet.entity.Account;
import com.example.wallet.filter.BalanceAmountZeroException;
import com.example.wallet.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountService;

    @MockBean
    private AccountRepository accountRepository;

    List<Account> accounts = new ArrayList<>();

    @BeforeEach
    void before() {
        Account account = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("11223344")
                .cifId(UUID.randomUUID())
                .balance(BigDecimal.valueOf(2000000.0))
                .cifId(UUID.randomUUID())
                .build();
        accounts.add(account);

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        when(accountRepository.findAllByCifId(account.getCifId())).thenReturn(accounts);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
    }

    @Test
    void should_return_all_account() {
        List<Account> accountByCifId = accountService.getAllAccountByCifId(accounts.get(0).getCifId());
        assertThat(accountByCifId).hasSameSizeAs(accounts);
    }

    @Test
    void should_return_one_account_by_account_number() {
        Account account1 = accountService.getAccount(accounts.get(0).getAccountNumber());
        assertThat(account1.getAccountNumber()).usingDefaultComparator().isEqualTo(accounts.get(0).getAccountNumber());
    }

    @Test
    void should_return_account_by_id() {
        Account accountById = accountService.getAccountById(accounts.get(0).getId());
        assertThat(accountById).isEqualTo(accounts.get(0));
    }

    @Test()
    void should_return_throw_entityNotFound() {

        Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.getAccountById(UUID.randomUUID()));

    }

    @Test
    void should_return_zero_account() {

        List<Account> allAccountByCifId = accountService.getAllAccountByCifId(UUID.randomUUID());

        assertThat(allAccountByCifId).isEmpty();
    }

    @Test
    void should_update_account_balance() {

        Account account = accountService
                .updateAccountBalance(accounts.get(0).getId(), BigDecimal.valueOf(1000000.0), "in");

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(3000000.0));
    }

    @Test
    void should_return_zero_exception() {

        Account account = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("11223344")
                .cifId(UUID.randomUUID())
                .balance(BigDecimal.valueOf(0.0))
                .cifId(UUID.randomUUID())
                .build();

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        Assertions.assertThrows(BalanceAmountZeroException.class, () -> accountService
                .updateAccountBalance(account.getId(), BigDecimal.valueOf(1000000.0), "out"));
    }
}