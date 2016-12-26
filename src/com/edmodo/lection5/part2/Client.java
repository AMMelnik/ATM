package com.edmodo.lection5.part2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pc on 28.11.2016.
 */
class Client implements Serializable {

    private String login;
    private String pin;
    private List<String> cardNumbers = Collections.synchronizedList(new ArrayList<>());
    private List<Integer> cardBalance = Collections.synchronizedList(new ArrayList<>());

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

    List<String> getCardNumbers() {
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

    List<Integer> getCardBalance() {
        return cardBalance;
    }

    void setCardBalance(int index, int balance) {
        this.cardBalance.set(index, balance);
    }
}
