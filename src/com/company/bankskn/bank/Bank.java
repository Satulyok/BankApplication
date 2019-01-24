package com.company.bankskn.bank;

import com.company.bankskn.card.Card;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.models.Account;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class Bank {

    Map<String, Set<Account>> bankCustomerAccount;

    public Map<String, Set<Account>> getBankCustomerAccount() {
        return bankCustomerAccount;
    }

    /**
     * This method check card ownership,validation of card.
     *
     * @param bank
     * @param card
     * @return
     * @throws InvalidAccountException
     */
    public boolean checkAccountValidation(Bank bank, Card card) throws InvalidAccountException {
        if (bank.getBankCustomerAccount().containsKey(card.getCardHolder())) {
            return true;
        }
        return false;
    }

    /**
     * Method to check is enough amount of money at bank account of customer
     * for get required money?
     *
     * @param card
     * @param cash
     * @return
     * @throws LowAccountBalanceException
     */
    public boolean checkAccountBalance(Card card, long cash) throws LowAccountBalanceException {
        long balance = getInitialBankBalance(card);
        if (cash <= balance) {
            return true;
        }
        return false;
    }

    /**
     * Method to answer can we withdraw amount of money from card, after checkings.
     *
     * @param card
     * @param cash
     * @return
     * @throws LowAccountBalanceException
     * @throws InvalidAccountException
     */
    public boolean canWithdrawFromCard(Card card, long cash) throws LowAccountBalanceException, InvalidAccountException {
        if (checkAccountValidation(this, card) && checkAccountBalance(card, cash)) {
            return true;
        }
        return false;
    }

    /**
     * Mathod to get initial balance of customer's account.
     *
     * @param card
     * @return
     */
    public long getInitialBankBalance(Card card) {
        Iterator<Account> accountItr = this.getBankCustomerAccount().get(card.getCardHolder()).iterator();
        while (accountItr.hasNext()) {
            Account accountBalance = accountItr.next();
            long balance = accountBalance.getAmount();
            return balance;
        }
        return 0;
    }

    /**
     * Method to withdraw amount of money from the bank account.
     *
     * @param card
     * @param amount
     * @return
     */
    public long withrawFromAccount(Card card, long amount) {
        Iterator<Account> accountItr = this.getBankCustomerAccount().get(card.getCardHolder()).iterator();
        while (accountItr.hasNext()) {
            Account account = accountItr.next();
            account.withrawAmount(amount);
            return account.getAmount();
        }
        return 0;
    }
}