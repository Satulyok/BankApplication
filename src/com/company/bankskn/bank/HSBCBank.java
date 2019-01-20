package com.company.bankskn.bank;

import java.util.HashMap;

/**
 * Singleton class
 */
public class HSBCBank extends Bank {

    private HSBCBank() {
    }

    private static HSBCBank hsbcBank;

    public static HSBCBank getInstance() {
        if(hsbcBank==null) {
            hsbcBank = new HSBCBank();
            hsbcBank.bankCustomerAccount =new HashMap<>();
        }
        return hsbcBank;
    }

    @Override
    public String toString() {
        return "HSBCBank{" +
                "bankCustomerAccount=" + bankCustomerAccount +
                '}';
    }
}