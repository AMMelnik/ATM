package com.edmodo.lection5.part2;

import java.util.Scanner;

/**
 * Created by pc on 28.11.2016.
 */
class ATM implements Terminal {
    static int restMoney = 430;
    int loginIndex;
    String login, pin, cardNumber;
    Card card = new Card();
    Client client = new Client();
    Account acc;

    void authorization() throws NoMoneyOnCardException {
        loginIndex = client.checkValidLogin(client.enterLogin());
        if (pinConfirmation(loginIndex)) {
            System.out.println("\u001b[34;m Здравствуйте, " + client.getClientName(loginIndex) + "!\n");
            menu1Level();
        }
    }

    boolean pinConfirmation(int index) throws NoMoneyOnCardException {
        loginIndex = index;
        int logonAttempt = 0;
        int maxLogon;
        boolean isCorrectLogon = false;
        for (int i = 0; i < 3; i++) {
            if (loginIndex != -1) {
                try {
                    int pinIndex = client.checkValidPin(client.enterPin());
                    if (pinIndex == loginIndex) {
                        isCorrectLogon = true;
                        break;
                    } else {
                        logonAttempt += 1;
                        maxLogon = 3 - logonAttempt;
                        System.out.println("\u001b[31;m Неправильный ПИН-код! Осталось попыток ввода : " + maxLogon + "\n");
                        if (maxLogon == 0) {
                            System.out.println("\u001b[31;m Превышено ограничение по авторизации! Аккаунт временно заблокирован\n");
                            Thread thread = new Thread();
                            String dot = ".";
                            for (int j = 0; j < 5; j++) {
                                System.out.print(dot);
                                dot += ".";
                                thread.sleep(2000);
                            }
                            System.out.println("\n");
                        }
                    }
                } catch (IncorrectPinException iPe) {
                    System.out.println(iPe.getMessage());
                    iPe.getIncorrectPinFormatMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\u001b[31;m Такого логина не существует. Необходимо зарегистрироваться!\n");
            }
        }
        return isCorrectLogon;
    }

    void menu1Level() throws NoMoneyOnCardException {
        boolean isChoiceEnd = false;
        while (!isChoiceEnd) {
            System.out.println("\u001b[34;m Какую операцию Вы хотите совершить?\n");
            System.out.println("\u001b[32;m (1) Добавить карту\n" + " (2) Удалить карту\n" + " (3) Удалить аккаунт\n"
                    + " (4) Выбрать карту\n" + " (5) Выйти\n");
            Scanner scMenu = new Scanner(System.in);
            String menuChoose = scMenu.nextLine();
            switch (menuChoose) {
                case "1":
                    pinConfirmation(loginIndex);
                    createCard();
                    System.out.println(acc.toString());
                    break;
                case "2":
                    pinConfirmation(loginIndex);
                    deleteCard();
                    break;
                case "3":
                    pinConfirmation(loginIndex);
                    deleteClient();
                    break;
                case "4":
                    pinConfirmation(loginIndex);
                    selectCard();
                    break;
                case "5":
                    isChoiceEnd = true;
                    break;
                default:
                    System.out.println("\u001b[34;m Пожалуйста, выберите нужное действие (1 - 5)\n");
                    break;
            }
        }
    }

    void menu2Level(int cardIndex) throws NoMoneyOnCardException {
        boolean isChoiceEnd = false;
        while (!isChoiceEnd) {
            System.out.println("\u001b[34;m Какую операцию Вы хотите совершить?\n");
            System.out.println("\u001b[32;m (1) Пополнить счет\n" + " (2) Снять наличные\n" + " (3) Запросить баланс\n"
                    + " (4) Выйти\n");
            Scanner scMenu = new Scanner(System.in);
            String menuChoose = scMenu.nextLine();
            switch (menuChoose) {
                case "1":
                    System.out.println(acc.toString());
                    pinConfirmation(loginIndex);
                    addMoney(cardIndex);
                    break;
                case "2":
                    pinConfirmation(loginIndex);
                    getMoney(cardIndex);
                    break;
                case "3":
                    pinConfirmation(loginIndex);
                    checkAccountStatus(cardIndex);
                    break;
                case "4":
                    isChoiceEnd = true;
                    break;
                default:
                    System.out.println("\u001b[34;m Пожалуйста, выберите нужное действие (1 - 4)\n");
                    break;
            }
        }
    }

    @Override
    public void checkAccountStatus(int cardIndex) {
        System.out.println("\u001b[34;m Баланс Вашей карты составляет " + acc.getCardBalance(cardIndex) + " рублей.\n");
    }

    @Override
    public void addMoney(int cardIndex) {
        System.out.println("\u001b[34;m Пополнение на " + acc.setCardBalance(cardIndex, 1000) + " рублей.\n");
    }

    @Override
    public void getMoney(int cardIndex) throws NoMoneyOnCardException {
        System.out.println("\u001b[34;m Снятие наличных на " + acc.setCardBalance(cardIndex, -500) + " рублей.\n");
    /*    int minusMoney = 1000;
        if (minusMoney > restMoney) {
            throw new NoMoneyOnCardException("\u001b[31;m Недостаточно средств на карте!", minusMoney);
        }

        restMoney = restMoney - minusMoney;
        return restMoney;*/
    }

    @Override
    public void createClient() {
        boolean isTruePin = false;
        boolean isTrueLogin = false;
        acc = new Account();
        acc.createAccount();
        while (!isTrueLogin) {
            try {
                login = client.enterLogin();
                client.checkLoginCreating(login);
                acc.setAccount(login);
                isTrueLogin = true;
            } catch (ClientDuplicatedException clDe) {
                System.out.println(clDe.getMessage());
                clDe.getDuplicatedLogin();
            }
        }
        while (!isTruePin) {
            try {
                pin = client.enterPin();
                client.addPinToClient(pin);
                acc.setAccount(pin);
                isTruePin = true;
            } catch (IncorrectPinException iPe) {
                System.out.println(iPe.getMessage());
                iPe.getIncorrectPinFormatMessage();
            }
        }
    }

    @Override
    public void deleteClient() {

    }

    @Override
    public void createCard() {
        boolean isTrueCard = false;
        while (!isTrueCard) {
            try {
                cardNumber = card.setCardNumber();
                acc.setAccount(cardNumber);
                isTrueCard = true;
            } catch (IncorrectCardNumberException iCnE) {
                System.out.println(iCnE.getMessage());
                iCnE.getIncorrectNumberFormatMessage();
            } catch (CardDuplicatedException caDe) {
                System.out.println(caDe.getMessage());
                caDe.getDuplicatedNumber();
            }

            acc.setCardsBalanceSize();
        }
    }

    @Override
    public void deleteCard() {

    }

    void selectCard() throws NoMoneyOnCardException {
        boolean isTrueCard = false;
        while (!isTrueCard) {
            acc.cardsViewer();
            Scanner scWhichCard = new Scanner(System.in);
            int whichCard = scWhichCard.nextInt();
            if (whichCard > 1 || whichCard < acc.getSize() - 1) {
                menu2Level(whichCard - 1);
                isTrueCard = true;
            }
        }
    }
}
