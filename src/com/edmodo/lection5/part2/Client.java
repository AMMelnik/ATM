package com.edmodo.lection5.part2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 28.11.2016.
 */
public class Client {

    ArrayList<String> clientsLoginList = new ArrayList<>();
    ArrayList<String> clientsPinList = new ArrayList<>();
    String login;


    String enterLogin() {
        System.out.println("\u001b[34;m Введите логин - имя клиента\n");
        Scanner scLogin = new Scanner(System.in);
        login = scLogin.nextLine();
        return login;
    }

    void checkLoginCreating(String login) throws ClientDuplicatedException {
        if (clientsLoginList.contains(login)) {
            throw new ClientDuplicatedException("\u001b[31;m Вы указали занятый логин!", login);
        }
        clientsLoginList.add(login);
    }

    int checkValidLogin(String login) {
        if (clientsLoginList.contains(login)) {
           return clientsLoginList.indexOf(login);
        }
        else return -1;
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

    void addPinToClient(String pin) {
        clientsPinList.add(pin);
    }

    int checkValidPin(String pin) {
        if (clientsPinList.contains(pin)) {
            return clientsPinList.indexOf(pin);
        }
        else return -1;
    }

    String getClientName(int index) {
        return clientsLoginList.get(index);
    }
}
