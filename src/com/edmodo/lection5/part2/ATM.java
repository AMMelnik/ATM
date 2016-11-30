package com.edmodo.lection5.part2;


import java.util.Scanner;

/**
 * Created by pc on 28.11.2016.
 */
public class ATM implements Terminal {
    static int restMoney = 430;
    int loginIndex;
    Card card = new Card();
    Client client = new Client();

    void authorization() throws NoMoneyOnCardException {
        loginIndex = client.checkValidLogin(client.enterLogin());
        if (pinConfirmation(loginIndex)) {
            System.out.println("\u001b[34;m Welcome, " + client.getClientName(loginIndex) + "!\n");
            menu();
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

    void menu() throws NoMoneyOnCardException {
        System.out.println("\u001b[34;m Какую операцию Вы хотите совершить?\n");
        System.out.println("\u001b[32;m (1) Пополнить счет\n" + " (2) Снять наличные\n" + " (3) Запросить баланс\n"
                + " (4) Добавить карту\n" + " (5) Удалить карту\n" + " (6) Удалить аккаунт\n" + " (7) Выйти\n");
        Scanner scMenu = new Scanner(System.in);
        String menuChoose = scMenu.nextLine();
        switch (menuChoose) {
            case "1":
                pinConfirmation(loginIndex);
                refillAccount();
                break;
            case "2":
                pinConfirmation(loginIndex);
                getMoney();
                break;
            case "3":
                pinConfirmation(loginIndex);
                checkAccountStatus();
                break;
            case "4":
                pinConfirmation(loginIndex);
                createCard();
                break;
            case "5":
                pinConfirmation(loginIndex);
                deleteCard();
                break;
            case "6":
                pinConfirmation(loginIndex);
                deleteClient();
                break;
            case "7":
                break;
            default:
                System.out.println("\u001b[34;m Пожалуйста, выберите нужное действие (1 - 7)\n");
                menu();
                break;
        }
    }

    @Override
    public int checkAccountStatus() {
        return 0;
    }

    @Override
    public int refillAccount() {
        return 0;
    }

    @Override
    public int getMoney() throws NoMoneyOnCardException {
        int minusMoney = 1000;
        if (minusMoney > restMoney) {
            throw new NoMoneyOnCardException("\u001b[31;m Недостаточно средств на карте!", minusMoney);
        }

        restMoney = restMoney - minusMoney;
        return restMoney;
    }

    @Override
    public void createClient() {
        boolean isTruePin = false;
        boolean isTrueLogin = false;
        while (!isTrueLogin) {
            try {
                client.checkLoginCreating(client.enterLogin());
                isTrueLogin = true;
            } catch (ClientDuplicatedException clDe) {
                System.out.println(clDe.getMessage());
                clDe.getDuplicatedLogin();
            }
        }
        while (!isTruePin) {
            try {
                client.addPinToClient(client.enterPin());
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
                String cardNumber = card.setCardNumber();
                isTrueCard = true;
            } catch (IncorrectCardNumberException iCnE) {
                System.out.println(iCnE.getMessage());
                iCnE.getIncorrectNumberFormatMessage();
            } catch (CardDuplicatedException caDe) {
                System.out.println(caDe.getMessage());
                caDe.getDuplicatedNumber();
            }

            int cardMoney = card.setCardMoney();
        }
    }

    @Override
    public void deleteCard() {

    }
}
