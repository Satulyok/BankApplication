package com.company.bankskn.manager.bankManager.Impl;

import com.company.bankskn.card.Card;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.manager.bankManager.BankManager;

public class BankManagerImpl implements BankManager {

    @Override
    public boolean isGetMoney(Card card, long amount) throws LowAccountBalanceException, InvalidAccountException {
        if(card.getIssuerBank().isReduceAmount(card, amount))
            return true;
        return false;
    }

    @Override
    public long getBalance(Card card, long amount) throws LowAccountBalanceException, InvalidAccountException {
        return card.getIssuerBank().getReducedBalance(card, amount);
    }

}
