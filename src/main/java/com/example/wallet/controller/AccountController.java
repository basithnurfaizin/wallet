package com.example.wallet.controller;

import com.example.wallet.dto.AccountResponse;
import com.example.wallet.dto.BaseResponse;
import com.example.wallet.entity.Account;
import com.example.wallet.service.AccountService;
import com.example.wallet.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/userId/{userId}")
    public ResponseEntity<BaseResponse<List<AccountResponse>>> getAllAccount(@PathVariable UUID userId) {

        List<Account> account = accountService.getAllAccountByCifId(userId);
        List<AccountResponse> accountResponses = account.stream().map(this::convertToResponse).collect(Collectors.toList());
        BaseResponse<List<AccountResponse>> response = new BaseResponse<>("success", "200", accountResponses);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/accountId/{accountId}")
    public ResponseEntity<BaseResponse<AccountResponse>> getAccountById(@PathVariable UUID accountId) {

        Account account = accountService.getAccountById(accountId);
        AccountResponse accountResponse = this.convertToResponse(account);
        BaseResponse<AccountResponse> response = new BaseResponse<>("success", "200", accountResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<BaseResponse<AccountResponse>> getAccountByAccountNumber(@PathVariable String accountNumber) {

        Account account = accountService.getAccount(accountNumber);
        AccountResponse accountResponse = this.convertToResponse(account);
        BaseResponse<AccountResponse> response = new BaseResponse<>("success", "200", accountResponse);

        return ResponseEntity.ok(response);
    }

    private AccountResponse convertToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getCifId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrencyId()
        );
    }
}
