package com.example.wallet.filter;

public class BalanceAmountZeroException extends RuntimeException {

    public BalanceAmountZeroException(String message) {
        super(message);
    }

    public BalanceAmountZeroException(String message, Throwable cause) {
        super(message,cause);
    }
}
