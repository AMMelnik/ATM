package com.edmodo.lection5.part2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 30.11.2016.
 */
class Account {

    ArrayList<Client> account = new ArrayList<>();

    void createAccount(String login, String pin) {
        Client client = new Client();
        client.setLogin(login);
        client.setPin(pin);
        account.add(client);
    }

    String enterLogin() {
        System.out.println("\u001b[34;m Введите логин - имя клиента\n");
        Scanner scLogin = new Scanner(System.in);
        String login = scLogin.nextLine();
        return login;
    }

    String getClientName(int index) {
        return account.get(index).getLogin();
    }

    // при создании логина
    int checkDuplicateLogin(String login) throws ClientDuplicatedException {
        for (int i = 0; i < account.size(); i++) {
            if (getClientName(i).equals(login)) {
                throw new ClientDuplicatedException("\u001b[31;m Вы указали занятый логин!", login);
            }
        }
        return 1;
    }

    // при авторизации
    int checkValidLogin(String login) {
        for (int i = 0; i < account.size(); i++) {
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
        for (int i = 0; i < account.size(); i++) {
            if (account.get(i).getPin().equals(pin)) {
                return i;
            }
        }
        return -1;
    }

    void deleteClientFromAccount(int clientIndex) {
        account.remove(clientIndex);
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
        for (int i = 0; i < account.size(); i++) {
            if (!account.get(i).getCardNumbers().contains(number)) {
                return -1;
            } else {
                throw new CardDuplicatedException("\u001b[31;m Вы указали номер карты, который уже используется!", number);
            }
        }
        return 0;
    }

    void setCardNumber(String number, int clientIndex) {
        if (getCardsSize(clientIndex) > 0) {
            account.get(clientIndex).setCardNumberWithZeroBalance(getCardsSize(clientIndex), number);
        } else {
            account.get(clientIndex).setCardNumberWithZeroBalance(0, number);
        }
    }

    int getCardBalance(int clientIndex, int cardIndex) {
        return account.get(clientIndex).getCardBalance(cardIndex);
    }

    void setCardBalance(int clientIndex, int cardIndex, int balance) {
        account.get(clientIndex).setCardBalance(cardIndex, getCardBalance(clientIndex, cardIndex) + balance);
    }

    int getCardsSize(int clientIndex) {
        return account.get(clientIndex).getCardNumbersSize();
    }

    int cardsViewer(int clientIndex) {
        if (getCardsSize(clientIndex) > 0) {
            System.out.println("\u001b[34;m Выберите карту для совершения операции: \n");
            for (int i = 0; i < getCardsSize(clientIndex); i++) {
                System.out.println("\u001b[32;m (" + (i + 1) + ") " + account.get(clientIndex).getCardNumber(i) + "\n");
            }
            return 1;
        } else return -1;
    }

    void deleteCardFromAccount(int clientIndex, int cardIndex) {
        account.get(clientIndex).getCardNumbers().remove(cardIndex);
        account.get(clientIndex).getCardBalance().remove(cardIndex);
    }
}




 /*


    //////////////////////////
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
        int balance = cardsBalance.get(cardIndex);
        cardsBalance.set(cardIndex, balance + money);
        return cardsBalance.get(cardIndex);
    }
}*/
