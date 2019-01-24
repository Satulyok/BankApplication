package com.company.bankskn.models;

public class ATM {

    private long balance = 500000;

    public long getBalance() {
        return balance;
    }

    public long withdraw(long amount) {
        balance -= amount;
        return balance;
    }
}