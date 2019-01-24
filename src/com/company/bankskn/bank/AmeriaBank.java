package com.company.bankskn.bank;

import java.util.HashMap;

/**
 * This class is the Singleton class, which is extends abstract Bank class.
 */
public class AmeriaBank extends Bank {

    private AmeriaBank() {
    }

    private static AmeriaBank ameriaBank;

    public static AmeriaBank getInstance() {

        if (ameriaBank == null) {
            ameriaBank = new AmeriaBank();
            ameriaBank.bankCustomerAccount = new HashMap<>();
        }
        return ameriaBank;
    }

    @Override
    public String toString() {
        return "AmeriaBank{" +
                "bankCustomerAccount=" + bankCustomerAccount +
                '}';
    }
}