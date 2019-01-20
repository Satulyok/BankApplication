package com.company.bankskn.parser;

import com.company.bankskn.bank.Bank;
import com.company.bankskn.card.Card;
import com.company.bankskn.enums.CardBrands;
import com.company.bankskn.enums.IssuerBank;
import com.company.bankskn.enums.Currency;
import com.company.bankskn.models.Account;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParsedCards {

    public List<Card> cards()  {
        List<Card> cardList = new ArrayList<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(
                    new File("src/com/company/bankskn/resources/cards.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("card");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                // Node nodeCardNumber = element.getElementsByTagName("cardnumber").item(0);
                String cardNumber = element.getElementsByTagName("cardnumber").item(0).getTextContent();
                CardBrands cardBrand = CardBrands.valueOf(element.getElementsByTagName("cardbrand").item(0).
                        getTextContent());
                Currency currency = Currency.valueOf(element.getElementsByTagName("currency").item(0).
                        getTextContent());
                Bank issuerBank = IssuerBank.valueOf(element.getElementsByTagName("issuerbank").item(0).
                        getTextContent()).getBankInstance();
                String cardHolder = element.getElementsByTagName("cardholder").item(0).getTextContent();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YY");
                Date expireDate = null;
                try {
                    expireDate = simpleDateFormat.parse(element.getElementsByTagName("expiredate").item(0).
                            getTextContent());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Card card = new Card.CardBuilder(Long.parseLong(cardNumber))
                        .setCardBrand(cardBrand)
                        .setCurrency(currency)
                        .setIssuerBank(issuerBank)
                        .setCardHolder(cardHolder)
                        .setExpireDate(expireDate)
                        .build();
                cardList.add(card);

                Bank bank = card.getIssuerBank();
                bank.getBankCustomerAccount().put(card.getCardHolder(),
                        new HashSet(Arrays.asList(new Account(card.getCardNumber(), (long) (Math.random() * 10000)))));
                // System.out.println("bank" + bank);
            }
        }

        return cardList;
    }

}