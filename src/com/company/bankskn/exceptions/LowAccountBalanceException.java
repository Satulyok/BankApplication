package com.company.bankskn.exceptions;

/**
 * exceptions thrown to indicate that there is insufficient balance
 * @author me
 */

public class LowAccountBalanceException extends Exception {

    public LowAccountBalanceException(String message) {
        super(message);
    }
}