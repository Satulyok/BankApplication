package com.company.bankskn.setup;


import com.company.bankskn.card.Card;
import com.company.bankskn.card.GenerateRandomCard;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.logger.LauncherLogger;
import com.company.bankskn.manager.atmManager.Impl.ATMManagerImpl;
import com.company.bankskn.manager.bankManager.Impl.BankManagerImpl;
import com.company.bankskn.service.ATMService;

import java.util.InputMismatchException;

public class TestMain {

    public static void main(String[] args) throws LowAccountBalanceException, InvalidAccountException {

        Thread thread = new Thread(new LauncherLogger());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.class.getName() + " is interrupted.");
        }

        Card card = GenerateRandomCard.getCard();
        System.out.println("----------------------------------------------------");
        ATMService atmService = new ATMService(new BankManagerImpl(), new ATMManagerImpl());
        java.util.Scanner input = new java.util.Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter the amount to withdraw:");
                Integer amountToWithdraw = input.nextInt();
                long amount = atmService.withdraw(card, amountToWithdraw);
                BankManagerImpl bankManagerImpl  = new BankManagerImpl();
                long newAmount = bankManagerImpl.getBalance(card, amount);
                System.out.println("Balance in your account is:  " + newAmount);
            } catch (InputMismatchException e) {
                System.out.println("Enter valid PIN code.");
            }
            break;
        }
    }
}






