package com.company.bankskn.exceptions;

/**
 * Exceptions thrown to indicate that customer's account balance
 * is not enough for getting required amount of money.
 */

public class LowAccountBalanceException extends Exception {

    public LowAccountBalanceException(String message) {
        super(message);
    }
}