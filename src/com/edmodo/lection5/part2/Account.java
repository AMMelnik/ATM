package com.edmodo.lection5.part2;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 30.11.2016.
 */
class Account implements Serializable {

    private ArrayList<Client> account;

     Account() {
         account = new ArrayList<>();
    }

    ArrayList<Client> getAccount() {
        return account;
    }

    void setAccount(ArrayList<Client> account) {
        this.account = account;
    }

    void createAccount(String login, String pin) {
        Client client = new Client();
        client.setLogin(login);
        client.setPin(pin);
        getAccount().add(client);
    }

    String enterLogin() {
        System.out.println("\u001b[34;m Введите логин - имя клиента\n");
        Scanner scLogin = new Scanner(System.in);
        return scLogin.nextLine();
    }

    String getClientName(int index) {
        return getAccount().get(index).getLogin();
    }

    // при создании логина
    int checkDuplicateLogin(String login) throws ClientDuplicatedException {
        for (int i = 0; i < getAccount().size(); i++) {
            if (getClientName(i).equals(login)) {
                throw new ClientDuplicatedException("\u001b[31;m Вы указали занятый логин!", login);
            }
        }
        return 1;
    }

    // при авторизации
    int checkValidLogin(String login) {
        for (int i = 0; i < getAccount().size(); i++) {
            if (getClientName(i).equals(login)) {
                return i;
            }
        }
        return -1;
    }

    String enterPin() throws IncorrectPinException {
        System.out.println("\u001b[34;m Введите ПИН-код для доступа к аккаунту\n");
        Scanner scPin = new Scanner(System.in);
        String pin = scPin.nextLine();
        Pattern pinPattern = Pattern.compile("\\d{4}");
        Matcher pinMatcher = pinPattern.matcher(pin);
        if (!pinMatcher.matches()) {
            throw new IncorrectPinException("\u001b[31;m Вы ввели некорректный ПИН-код!");
        }
        return pin;
    }

    int checkValidPin(String pin) {
        for (int i = 0; i < getAccount().size(); i++) {
            if (getAccount().get(i).getPin().equals(pin)) {
                return i;
            }
        }
        return -1;
    }

    void deleteClientFromAccount(int clientIndex) {
        getAccount().remove(clientIndex);
    }

    String enterCardNumber() throws IncorrectCardNumberException {
        System.out.println("\u001b[34;m Введите номер карты (формат XXXX XXXX XXXX XXXX)\n");
        Scanner scCardNumber = new Scanner(System.in);
        String number = scCardNumber.nextLine();
        Pattern cardNumberPattern = Pattern.compile("(\\d{4}\\s){3}\\d{4}");
        Matcher cardNumberMatcher = cardNumberPattern.matcher(number);
        if (!cardNumberMatcher.matches()) {
            throw new IncorrectCardNumberException("\u001b[31;m Номер карты не соответствует формату!");
        }
        return number;
    }

    int checkValidCardNumber(String number) throws CardDuplicatedException {
        for (int i = 0; i < getAccount().size(); i++) {
            if (getAccount().get(i).getCardNumbers().contains(number)) {
                throw new CardDuplicatedException("\u001b[31;m Вы указали номер карты, который уже используется!", number);
            }
        }
        return -1;
    }

    String getCardNumber(int clientIndex, int cardIndex) {
        return getAccount().get(clientIndex).getCardNumber(cardIndex);
    }

    void setCardNumber(String number, int clientIndex) {
        if (getCardsSize(clientIndex) > 0) {
            getAccount().get(clientIndex).setCardNumberWithZeroBalance(getCardsSize(clientIndex), number);
        } else {
            getAccount().get(clientIndex).setCardNumberWithZeroBalance(0, number);
        }
    }

    int getCardBalance(int clientIndex, int cardIndex) {
        return getAccount().get(clientIndex).getCardBalance(cardIndex);
    }

    void setCardBalance(int clientIndex, int cardIndex, int sum) {
        getAccount().get(clientIndex).setCardBalance(cardIndex, getCardBalance(clientIndex, cardIndex) + sum);
    }

    int getCardsSize(int clientIndex) {
        return getAccount().get(clientIndex).getCardNumbersSize();
    }

    int cardsViewer(int clientIndex) {
        if (getCardsSize(clientIndex) > 0) {
            System.out.println("\u001b[34;m Выберите карту для совершения операции: \n");
            for (int i = 0; i < getCardsSize(clientIndex); i++) {
                System.out.println("\u001b[32;m (" + (i + 1) + ") " + getAccount().get(clientIndex).getCardNumber(i) + "\n");
            }
            return 1;
        } else return -1;
    }

    void deleteCardFromAccount(int clientIndex, int cardIndex) {
        getAccount().get(clientIndex).getCardNumbers().remove(cardIndex);
        getAccount().get(clientIndex).getCardBalance().remove(cardIndex);
    }
}