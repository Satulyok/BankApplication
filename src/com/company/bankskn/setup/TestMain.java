package com.company.bankskn.setup;


import com.company.bankskn.card.Card;
import com.company.bankskn.card.GenerateRandomCard;
import com.company.bankskn.exceptions.InvalidAccountException;
import com.company.bankskn.exceptions.LowAccountBalanceException;
import com.company.bankskn.manager.atmManager.Impl.ATMManagerImpl;
import com.company.bankskn.manager.bankManager.Impl.BankManagerImpl;
import com.company.bankskn.service.ATMService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws LowAccountBalanceException, InvalidAccountException, IOException {
        List<Card> cards = new ArrayList<>();
        while (true) {
            Card card1 = GenerateRandomCard.getCard();
            Card card2 = GenerateRandomCard.getCard();
            if (card2.getCardNumber() == card1.getCardNumber()) {
                continue;
            } else {
                cards.add(card1);
                cards.add(card2);
                break;
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Initial amount of the first Card:" + cards.get(0).getIssuerBank().getInitialBankBalance(cards.get(0)));
        System.out.println("Initial amount of the second Card:" + cards.get(1).getIssuerBank().getInitialBankBalance(cards.get(1)));

        ATMService atmService = new ATMService(new BankManagerImpl(), new ATMManagerImpl());
        java.util.Scanner input = new java.util.Scanner(System.in);
        try {
            System.out.println("Please enter the amount to withdraw from first card:");
            Integer amountToWithdraw1 = input.nextInt();

            System.out.println("Please enter the amount to withdraw from second card:");
            Integer amountToWithdraw2 = input.nextInt();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println("Withdrawing money from first Card");
                            long res = atmService.withdrawFirst(cards.get(0), amountToWithdraw1);
                            System.out.println("(First Card)Balance in your account is:  " + cards.get(0).getIssuerBank().getInitialBankBalance(cards.get(0)));
                            if (res == 0)
                                break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println("Withdrawing money from second Card");
                            long res = atmService.withdrawSecond(cards.get(1), amountToWithdraw2);
                            System.out.println("(First Card)Balance in your account is:  " + cards.get(1).getIssuerBank().getInitialBankBalance(cards.get(1)));
                            if (res == 0)
                                break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });


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






