package com.company.bankskn.service;

import com.company.bankskn.card.Card;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowATMBalanceException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.logger.Logger;
import com.company.bankskn.manager.atmManager.Impl.ATMManagerImpl;
import com.company.bankskn.manager.bankManager.Impl.BankManagerImpl;
import com.company.bankskn.models.ATM;

import java.io.IOException;

public class ATMService {

    private BankManagerImpl bankManagerImpl;
    private ATMManagerImpl atmManagerImpl;
    private ATM atm = new ATM();

    public ATMService (BankManagerImpl bankManagerImpl, ATMManagerImpl atmManagerImpl) {
        this.bankManagerImpl = bankManagerImpl;
        this.atmManagerImpl = atmManagerImpl;
    }

    public long withdraw(Card card, long amount) {
        try {
            if (bankManagerImpl.isGetMoney(card, amount)) {
                if (atmManagerImpl.checkATMBalance(atm, amount)) {
                    atmManagerImpl.reduceATMBalance(atm, amount);
                    Logger.logMessage("Transaction was successful!");
                    return amount;
                }
                else {
                    throw new LowATMBalanceException("Low ATM Balance!");
                }
            } else {
                throw new LowAccountBalanceException("Low Account Balance!");
            }
        } catch (LowATMBalanceException e) {
            System.out.println(e.getMessage());
        } catch (LowAccountBalanceException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAccountException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public long seeBalance(Card card, long amount) {
//        try {
//            return bankManagerImpl.getBalance(card, withdraw(card, amount));
//        } catch (LowAccountBalanceException e) {
//            e.printStackTrace();
//        } catch (InvalidAccountException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

}