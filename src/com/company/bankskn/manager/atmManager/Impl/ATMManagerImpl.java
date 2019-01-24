package com.company.bankskn.manager.atmManager.Impl;

import com.company.bankskn.exceptions.LowATMBalanceException;
import com.company.bankskn.manager.atmManager.ATMManager;
import com.company.bankskn.models.ATM;

public class ATMManagerImpl implements ATMManager {

    /**
     * Checks is the balance of ATM enough for transaction?
     *
     * @param atm
     * @param amount
     * @return
     * @throws LowATMBalanceException
     */
    @Override
    public boolean checkATMBalance(ATM atm, long amount) throws LowATMBalanceException {
        if (amount <= atm.getBalance())
            return true;
        return false;
    }

    /**
     * Method for getting new balance in ATM, after transaction.
     *
     * @param atm
     * @param amount
     * @return
     * @throws LowATMBalanceException
     */
    @Override
    public long withdrawFromATM(ATM atm, long amount) throws LowATMBalanceException {
        long atmNewBalance = atm.withdraw(amount);
        return atmNewBalance;
    }
}