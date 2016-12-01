package com.edmodo.lection5.part2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by pc on 30.11.2016.
 */
class Account {
    ArrayList<Integer> cardsBalance;
    LinkedList<String> account;

    void createAccount() {
        account = new LinkedList<>();
        cardsBalance = new ArrayList<>();
    }

    void setAccount(String data) {
        if (!account.contains(data)) {
            account.add(data);
        } else {
            System.out.println("\u001b[31;m Эта карта уже добавлена!\n");
        }
    }

    @Override
    public String toString() {
        return account.toString();
    }

    int getSize() {
        return account.size();
    }

    void cardsViewer() {
        System.out.println("\u001b[34;m Выберите карту для совершения операции: \n");
        for (int i = 2; i < account.size(); i++) {
            System.out.println("\u001b[32;m (" + (i - 1) + ") " + account.get(i) + "\n");
        }

    }

    void setCardsBalanceSize() {
        for (int i = 0; i < getSize() - 2; i++) {
            cardsBalance.add(0);
        }
    }

    int getCardBalance(int cardIndex) {
        return cardsBalance.get(cardIndex);
    }

    int setCardBalance(int cardIndex, int money) {
      /*  int balance = cardsBalance.get(cardIndex) + money;
        return  balance;*/
        int balance = cardsBalance.get(cardIndex);
        cardsBalance.set(cardIndex, balance + money);
        return cardsBalance.get(cardIndex);
    }
}
