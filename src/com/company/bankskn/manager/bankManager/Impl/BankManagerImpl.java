package com.company.bankskn.manager.bankManager.Impl;

import com.company.bankskn.bank.Bank;
import com.company.bankskn.card.Card;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.manager.bankManager.BankManager;

public class BankManagerImpl implements BankManager {

    @Override
    public boolean isGetMoney(Card card, long amount) throws LowAccountBalanceException, InvalidAccountException {
        Bank issuerBank = card.getIssuerBank();
        if(issuerBank.canWithdrawFromCard(card, amount))
            return true;
        return false;
    }

    @Override
    public long getBalance(Card card) throws LowAccountBalanceException, InvalidAccountException {
        return card.getIssuerBank().getInitialBankBalance(card);
    }

    @Override
    public long withrawFromAccount(Card card, long amount) throws LowAccountBalanceException, InvalidAccountException {
        return card.getIssuerBank().withrawFromAccount(card, amount);
    }
}
