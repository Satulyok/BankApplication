package com.company.bankskn.bank;

import com.company.bankskn.card.Card;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.models.Account;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public  abstract class Bank {

        Map<String, Set<Account>> bankCustomerAccount;

    public Map<String, Set<Account>> getBankCustomerAccount() {
        return bankCustomerAccount;
    }


    public boolean checkAccountValidation(Bank bank, Card card) throws InvalidAccountException {
        if (bank.getBankCustomerAccount().containsKey(card.getCardHolder())) {
            return true;
        }
        return false;
    }

    public boolean checkAccountBalance(Card card, long cash) throws LowAccountBalanceException {
        long balance = getInitialBankBalance(card);
        if (cash <= balance){
            return true;
        }
        return false;
    }


    public boolean canWithdrawFromCard(Card card, long cash) throws LowAccountBalanceException, InvalidAccountException {
        if (checkAccountValidation(this, card) && checkAccountBalance(card, cash)){
            return true;
        }
        return false;
    }

    public long getInitialBankBalance (Card card) {
        Iterator<Account> accountItr = this.getBankCustomerAccount().get(card.getCardHolder()).iterator();
        while (accountItr.hasNext()) {
            Account accountBalance = accountItr.next();
            long balance = accountBalance.getAmount();
            return balance;
        }
        return 0;
    }

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