package com.company.bankskn.setup;


import com.company.bankskn.card.Card;
import com.company.bankskn.card.GenerateRandomCard;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.manager.atmManager.Impl.ATMManagerImpl;
import com.company.bankskn.manager.bankManager.Impl.BankManagerImpl;
import com.company.bankskn.service.ATMService;

import java.io.IOException;
import java.util.InputMismatchException;

public class TestMain {

    public static void main(String[] args) throws LowAccountBalanceException, InvalidAccountException, IOException {
        Card card1 = GenerateRandomCard.getCard();
        Card card2 = GenerateRandomCard.getCard();
        System.out.println("----------------------------------------------------");
        ATMService atmService = new ATMService(new BankManagerImpl(), new ATMManagerImpl());
        java.util.Scanner input = new java.util.Scanner(System.in);
        try {
            System.out.println("Please enter the amount to withdraw:");
            Integer amountToWithdraw = input.nextInt();

            Thread t1 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        while (true)
                        {
                            System.out.println("Withdrawing money from second Thread");
                            long newAmount = atmService.withdrawSecond(card1, amountToWithdraw);
                            System.out.println("(Second Thread)Balance in your account is:  " + newAmount);
                            if(newAmount == 0)
                                break;
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread t2 = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        while (true)
                        {
                            System.out.println("Withdrawing money from first Thread");
                            long newAmount = atmService.withdrawFirst(card2, amountToWithdraw);
                            System.out.println("(First Thread)Balance in your account is:  " + newAmount);
                            if(newAmount == 0)
                                break;
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Start both threads
            t1.start();
            t2.start();

            t1.join();
            t2.join();

        } catch (InputMismatchException e) {
            System.out.println("Enter valid PIN code.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}






