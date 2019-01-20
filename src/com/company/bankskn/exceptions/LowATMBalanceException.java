package com.company.bankskn.exceptions;

/**
 * exceptions thrown to indicate that there is insufficient
 * balance in ATM.
 * @author me
 */

public class LowATMBalanceException extends Exception {

    public LowATMBalanceException(String message) {
        super(message);
    }
}