package com.edmodo.lection5.part2;

import java.util.LinkedList;

/**
 * Created by pc on 30.11.2016.
 */
class Account {
    int cardBalance = 0;
    LinkedList<String> account;

    void createAccount() {
        account = new LinkedList<>();
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

    int cardsViewer() {
        int numberPointStart = 2;
        int numberPointEnd = numberPointStart;
        System.out.println("\u001b[34;m Выберите карту для совершения операции: \n");
        for (int i = 2; i < account.size(); i++) {
            numberPointEnd = i;
            System.out.println("\u001b[32;m (" + (numberPointEnd - 1) + ") " + account.get(i) + "\n");
        }
        return numberPointStart & numberPointEnd;
    }

    int getCardBalance() {
        return cardBalance;
    }

    int setCardBalance(int money) {
        return cardBalance += money;
    }
}
