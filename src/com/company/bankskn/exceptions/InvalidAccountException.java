package com.company.bankskn.exceptions;

/**
 * exceptions thrown to check account validation by Bank
 *
 */
public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}