package com.edmodo.lection5.part2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 28.11.2016.
 */
class Card {
    ArrayList<String> cardNumbersList = new ArrayList<>();

    String setCardNumber() throws IncorrectCardNumberException, CardDuplicatedException {
        System.out.println("\u001b[34;m Введите номер карты (формат XXXX XXXX XXXX XXXX)\n");
        Scanner scCardNumber = new Scanner(System.in);
        String number = scCardNumber.nextLine();
        Pattern cardNumberPattern = Pattern.compile("(\\d{4}\\s){3}\\d{4}");
        Matcher cardNumberMatcher = cardNumberPattern.matcher(number);
        if (!cardNumberMatcher.matches()) {
            throw new IncorrectCardNumberException("\u001b[31;m Номер карты не соответствует формату!");
        }
        if (cardNumbersList.contains(number)) {
            throw new CardDuplicatedException("\u001b[31;m Вы указали занятый номер карты!", number);
        }
        cardNumbersList.add(number);
        return number;
    }
}

