package com.company.bankskn.exceptions;

/**
 * Exceptions thrown to check account validation by Bank
 */
public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}