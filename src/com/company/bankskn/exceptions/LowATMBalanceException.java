package com.company.bankskn.exceptions;

/**
 * Exceptions thrown to indicate that there is not enough balance in ATM
 * to get required amount of money.
 */

public class LowATMBalanceException extends Exception {

    public LowATMBalanceException(String message) {
        super(message);
    }
}