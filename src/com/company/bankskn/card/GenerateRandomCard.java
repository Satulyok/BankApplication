package com.company.bankskn.card;

/**
 * GenerateRandomCard class is responsible
 * for getting random card from the card list.
 */


import com.company.bankskn.logger.Logger;
import com.company.bankskn.models.Account;
import com.company.bankskn.parser.ParsedCards;

import java.io.IOException;
import java.util.*;

public class GenerateRandomCard {

    public static Card getCard() throws IOException {

        List<Card> cardList = new ArrayList();
        cardList.addAll(new ParsedCards().cards());

        int index = (int) (Math.random() * cardList.size());
        Card card = cardList.get(index);

        try {
            Logger.logMessage("getting card..");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.logMessage("Random generated card");
        Logger.logMessage("CardHolder's name: " + card.getCardHolder());
        Logger.logMessage("Card number is: " + card.getCardNumber());
        Iterator<Account> accountItr = card.getIssuerBank().getBankCustomerAccount().get(card.getCardHolder()).iterator();
        while (accountItr.hasNext()) {
            Account accountBalance = accountItr.next();
            Logger.logMessage("Account balance is: " + accountBalance.getAmount() + " " + card.getCurrency());
        }
        return card;
    }
}