package com.company.bankskn.manager.atmManager;

import com.company.bankskn.exceptions.LowATMBalanceException;
import com.company.bankskn.models.ATM;

public interface ATMManager {

    boolean checkATMBalance(ATM atm, long amount) throws LowATMBalanceException;

    long reduceATMBalance(ATM atm, long amount) throws LowATMBalanceException;
}