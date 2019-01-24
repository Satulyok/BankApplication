package com.company.bankskn.manager.atmManager.Impl;

import com.company.bankskn.exceptions.LowATMBalanceException;
import com.company.bankskn.manager.atmManager.ATMManager;
import com.company.bankskn.models.ATM;

public class ATMManagerImpl implements ATMManager {

    @Override
    public boolean checkATMBalance(ATM atm, long amount) throws LowATMBalanceException {
        if(amount<=atm.getBalance())
            return true;
        return false;
    }

    @Override
    public long withdrawFromATM(ATM atm, long amount) throws LowATMBalanceException {
        long atmNewBalance = atm.withdraw(amount);
        return atmNewBalance;
    }
}