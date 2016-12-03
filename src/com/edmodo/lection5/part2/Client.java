package com.edmodo.lection5.part2;

import java.util.ArrayList;

/**
 * Created by pc on 28.11.2016.
 */
class Client {

    private String login;
    private String pin;
    private ArrayList<String> cardNumbers = new ArrayList<String>();
    private ArrayList<Integer> cardBalance = new ArrayList<Integer>();

    String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    String getPin() {
        return pin;
    }

    void setPin(String pin) {
        this.pin = pin;
    }

    String getCardNumber(int index) {
        return cardNumbers.get(index);
    }

    ArrayList<String> getCardNumbers() {
        return cardNumbers;
    }

    void setCardNumberWithZeroBalance(int index, String number) {
        this.cardNumbers.add(index, number);
        this.cardBalance.add(index, 0);
    }

    int getCardNumbersSize() {
        return cardNumbers.size();
    }

    int getCardBalance(int index) {
        return cardBalance.get(index);
    }

    ArrayList<Integer> getCardBalance() {
        return cardBalance;
    }

    void setCardBalance(int index, int balance) {
        this.cardBalance.set(index, balance);
    }
}
