package com.company.bankskn.models;

public class Account {
    private long cardNumber;
    private long amount;

    public Account(long cardNumber, long amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void withrawAmount(long amount) {
        this.amount -= amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNumber=" + cardNumber +
                ", amount=" + amount +
                '}';
    }
}
