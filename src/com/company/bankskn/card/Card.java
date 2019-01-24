package com.company.bankskn.card;

import com.company.bankskn.bank.Bank;
import com.company.bankskn.enums.CardBrands;
import com.company.bankskn.enums.Currency;

import java.util.*;
import java.util.stream.Stream;
import java.util.Date;

/**
 * This class is written by Builder pattern.
 * Information about Card class is taken from cards.xml file.
 */
public class Card {
    private final long cardNumber;
    private final CardBrands cardBrand;
    private final Currency currency;
    private final Bank issuerBank;
    private final String cardHolder;
    private final Date expireDate;


    private Card(CardBuilder builder) {
        this.cardNumber = builder.cardNumber;
        this.cardBrand = builder.cardBrand;
        this.currency = builder.currency;
        this.issuerBank = builder.issuerBank;
        this.cardHolder = builder.cardHolder;
        this.expireDate = builder.expireDate;
    }


    public long getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public Bank getIssuerBank() {
        return issuerBank;
    }

    public Currency getCurrency() {
        return currency;
    }


    public static class CardBuilder {
        private long cardNumber;
        private CardBrands cardBrand;
        private Currency currency;
        private Bank issuerBank;
        private String cardHolder;
        private Date expireDate;

        public CardBuilder(long cardNumber) {
            this.cardNumber = cardNumber;
        }


        public CardBuilder setCardBrand(CardBrands cardBrand) {
            this.cardBrand = cardBrand;
            return this;
        }

        public CardBuilder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public CardBuilder setIssuerBank(Bank issuerBank) {
            this.issuerBank = issuerBank;
            return this;
        }

        public CardBuilder setCardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public CardBuilder setExpireDate(Date expireDate) {
            this.expireDate = expireDate;
            return this;
        }


        public Card build() {
            if (Stream.of(cardNumber, cardBrand, currency, issuerBank, cardHolder, expireDate)
                    .anyMatch(Objects::isNull)) {
                throw new IllegalStateException("Any required fields are not instantiated.");
            }
            return new Card(this);
        }
    }

    @Override
    public String toString() {
        return "card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardBrand='" + cardBrand + '\'' +
                ", currency='" + currency + '\'' +
                ", issuerBank='" + issuerBank + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }
}