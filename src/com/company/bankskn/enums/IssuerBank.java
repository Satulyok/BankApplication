package com.company.bankskn.enums;

import com.company.bankskn.bank.AmeriaBank;
import com.company.bankskn.bank.Bank;
import com.company.bankskn.bank.HSBCBank;

public enum IssuerBank {
    AMERIA {
        @Override
        public Bank getBankInstance() {
            return AmeriaBank.getInstance();
        }
    },

    HSBC {
        @Override
        public Bank getBankInstance() {
            return HSBCBank.getInstance();
        }
    };

    public abstract Bank getBankInstance();
}