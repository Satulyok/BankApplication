package com.company.bankskn.manager.bankManager;

import com.company.bankskn.card.Card;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;

public interface BankManager  {

    boolean isGetMoney(Card card, long amount) throws LowAccountBalanceException, InvalidAccountException;
    long getBalance(Card card) throws LowAccountBalanceException, InvalidAccountException;
    long withrawFromAccount(Card card, long amount) throws LowAccountBalanceException, InvalidAccountException;

}