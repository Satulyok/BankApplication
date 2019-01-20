package com.company.bankskn.card;

/**
 * GenerateRandomCard class is responsible
 * for getting random card from the card list.
 */


import com.company.bankskn.logger.LauncherLogger;
import com.company.bankskn.models.Account;
import com.company.bankskn.parser.ParsedCards;

import java.io.IOException;
import java.util.*;

public class GenerateRandomCard {

    public static Card getCard() {

        List<Card> cardList = new ArrayList();
        cardList.addAll(new ParsedCards().cards());

        int index = (int) (Math.random() * cardList.size());
        Card card = cardList.get(index);

        try {
            LauncherLogger.launcherLogMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Random generated card");
        System.out.println("CardHolder's name: " + card.getCardHolder());
        System.out.println("Card number is: " + card.getCardNumber());
        Iterator<Account> accountItr = card.getIssuerBank().getBankCustomerAccount().get(card.getCardHolder()).iterator();
        while (accountItr.hasNext()) {
            Account accountBalance = accountItr.next();
            System.out.println("Account balance is: " + accountBalance.getAmount() + " " + card.getCurrency());
        }
        return card;
    }
}