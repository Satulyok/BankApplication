package com.company.bankskn.setup;


import com.company.bankskn.card.Card;
import com.company.bankskn.card.GenerateRandomCard;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.logger.Logger;
import com.company.bankskn.manager.atmManager.Impl.ATMManagerImpl;
import com.company.bankskn.manager.bankManager.Impl.BankManagerImpl;
import com.company.bankskn.service.ATMService;

import java.io.IOException;
import java.util.InputMismatchException;

public class TestMain {

    public static void main(String[] args) throws LowAccountBalanceException, InvalidAccountException, IOException {
        Card card = GenerateRandomCard.getCard();
        Logger.logMessage("----------------------------------------------------");
        ATMService atmService = new ATMService(new BankManagerImpl(), new ATMManagerImpl());
        java.util.Scanner input = new java.util.Scanner(System.in);
        try {
            Logger.logMessage("Please enter the amount to withdraw:");
            Integer amountToWithdraw = input.nextInt();
            long amount = atmService.withdraw(card, amountToWithdraw);
            BankManagerImpl bankManagerImpl  = new BankManagerImpl();
            long newAmount = bankManagerImpl.getBalance(card, amount);
            Logger.logMessage("Balance in your account is:  " + newAmount);
        } catch (InputMismatchException e) {
            Logger.logMessage("Enter valid PIN code.");
        }
    }
}






