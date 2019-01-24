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
    private volatile boolean isFinished = true;

    public ATMService (BankManagerImpl bankManagerImpl, ATMManagerImpl atmManagerImpl) {
        this.bankManagerImpl = bankManagerImpl;
        this.atmManagerImpl = atmManagerImpl;
    }

    public long withdraw(Card card, long amount) {
        try {
            if (bankManagerImpl.isGetMoney(card, amount)) {
                if (atmManagerImpl.checkATMBalance(atm, amount)) {
                    bankManagerImpl.withdrawFromAccount(card,amount);
                    atmManagerImpl.withdrawFromATM(atm, amount);
                    Logger.logMessage("Transaction was successful!");
                    return bankManagerImpl.getBalance(card);
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

    public long withdrawFirst(Card card, long amount) throws InterruptedException {
        synchronized (this)
        {
            while (isFinished)
                wait();
            long a = withdraw(card,amount);

            Thread.sleep(1000);
            isFinished = true;
            notify();
            return a;
        }
    }

    public long withdrawSecond(Card card, long amount) throws InterruptedException {
        synchronized (this)
        {
            while (!isFinished)
                wait();
            long a = withdraw(card,amount);

            Thread.sleep(1000);
            isFinished = false;
            notify();
            return a;
        }
    }
}