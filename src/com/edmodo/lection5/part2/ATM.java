package com.edmodo.lection5.part2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by pc on 28.11.2016.
 */
class ATM implements Terminal {
    int loginIndex;
    Account acc = new Account();

    void authorization() throws NoMoneyOnCardException {
        loginIndex = acc.checkValidLogin(acc.enterLogin());
        if (pinConfirmation(loginIndex)) {
            System.out.println("\u001b[34;m Здравствуйте, " + acc.getClientName(loginIndex) + "!\n");
            menu1Level();
        }
    }

    boolean pinConfirmation(int index) throws NoMoneyOnCardException {
        loginIndex = index;
        int logonAttempt = 1;
        int maxLogon;
        boolean isCorrectLogon = false;
        if (loginIndex != -1) {
            for (int i = 0; i < 3; i++) {
                maxLogon = 3 - logonAttempt;
                try {
                    int pinIndex = acc.checkValidPin(acc.enterPin());
                    if (pinIndex == loginIndex) {
                        isCorrectLogon = true;
                        break;
                    } else {
                        logonAttempt += 1;
                        System.out.println("\u001b[31;m Неправильный ПИН-код!\n");
                        System.out.println("\u001b[31;m Осталось попыток ввода : " + maxLogon + "\n");
                    }
                } catch (IncorrectPinException iPe) {
                    logonAttempt += 1;
                    System.out.println(iPe.getMessage());
                    iPe.getIncorrectPinFormatMessage();
                    System.out.println("\u001b[31;m Осталось попыток ввода : " + maxLogon + "\n");
                }
                if (maxLogon == 0) {
                    lockAccount();
                }
            }
        } else {
            System.out.println("\u001b[31;m Такого логина не существует. Необходимо зарегистрироваться!\n");
        }
        return isCorrectLogon;
    }

    void lockAccount() {
        System.out.println("\u001b[31;m Превышено ограничение по авторизации! Аккаунт временно заблокирован\n");
        Thread thread = new Thread();
        String dot = ".";
        for (int j = 0; j < 5; j++) {
            System.out.print(dot);
            dot += ".";
            try {
                thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");
    }

    void menu1Level() throws NoMoneyOnCardException {
        boolean isChoiceEnd = false;
        while (!isChoiceEnd) {
            System.out.println("\u001b[34;m Какое действие Вы хотите выполнить?\n");
            System.out.println("\u001b[32;m (1) Добавить карту\n" + " (2) Удалить аккаунт\n"
                    + " (3) Выбрать карту для совершения операций\n" + " (4) Выйти\n");
            Scanner scMenu = new Scanner(System.in);
            String menuChoose = scMenu.nextLine();
            switch (menuChoose) {
                case "1":
                    if (pinConfirmation(loginIndex)) {
                        System.out.println("\u001b[32;m Карта " + createCard(loginIndex) + " добавлена к Вашему аккаунту\n");
                        break;
                    } else break;
                case "2":
                    if (pinConfirmation(loginIndex)) {
                        deleteClient(loginIndex);
                        isChoiceEnd = true;
                        break;
                    } else break;
                case "3":
                    if (pinConfirmation(loginIndex)) {
                        selectCard(loginIndex);
                        break;
                    } else break;
                case "4":
                    isChoiceEnd = true;
                    break;
                default:
                    System.out.println("\u001b[34;m Пожалуйста, выберите нужное действие (1 - 4)\n");
                    break;
            }
        }
    }

    void menu2Level(int cardIndex) throws NoMoneyOnCardException {
        boolean isChoiceEnd = false;
        while (!isChoiceEnd) {
            System.out.println("\u001b[34;m Какую операцию Вы хотите совершить?\n");
            System.out.println("\u001b[32;m (1) Пополнить счет\n" + " (2) Снять наличные\n" + " (3) Запросить баланс\n"
                    + " (4) Удалить карту\n" + " (5) Выйти\n");
            Scanner scMenu = new Scanner(System.in);
            String menuChoose = scMenu.nextLine();
            switch (menuChoose) {
                case "1":
                    if (pinConfirmation(loginIndex)) {
                        addMoney(loginIndex, cardIndex);
                        break;
                    } else break;
                case "2":
                    if (pinConfirmation(loginIndex)) {
                        try {
                            getMoney(loginIndex, cardIndex);
                        } catch (NoMoneyOnCardException nMoCe) {
                            System.out.println(nMoCe.getMessage());
                            nMoCe.getNeedMoney();
                        }
                        break;
                    } else break;
                case "3":
                    if (pinConfirmation(loginIndex)) {
                        checkAccountStatus(loginIndex, cardIndex);
                        break;
                    } else break;
                case "4":
                    if (pinConfirmation(loginIndex)) {
                        deleteCard(loginIndex, cardIndex);
                        isChoiceEnd = true;
                        break;
                    } else break;
                case "5":
                    isChoiceEnd = true;
                    break;
                default:
                    System.out.println("\u001b[34;m Пожалуйста, выберите нужное действие (1 - 5)\n");
                    break;
            }
        }
    }

    @Override
    public void checkAccountStatus(int clientIndex, int cardIndex) {
        System.out.println("\u001b[34;m Баланс Вашей карты составляет " + acc.getCardBalance(clientIndex, cardIndex) + " рублей.\n");
    }

    @Override
    public void addMoney(int clientIndex, int cardIndex) {
        boolean isTrueAdding = false;
        while (!isTrueAdding) {
            System.out.println("\u001b[34;m Какую сумму Вы хотите внести?\n");
            System.out.println("\u001b[31;m пополнение счета можно производить на сумму, кратную 100 руб.\n");
            Scanner scMoney = new Scanner(System.in);
            try {
                int money = scMoney.nextInt();
                if (money % 100 == 0) {
                    acc.setCardBalance(clientIndex, cardIndex, money);
                    System.out.println("\u001b[34;m Пополнение на " + money + " рублей.\n");
                    isTrueAdding = true;
                } else {
                    System.out.println("\u001b[31;m пополнение счета можно производить на сумму, кратную 100 руб.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001b[31;m Пожалуйста, укажите корректную сумму!");
            }
        }
    }

    @Override
    public void getMoney(int clientIndex, int cardIndex) throws NoMoneyOnCardException {
        boolean isTrueGetting = false;
        int balance = acc.getCardBalance(clientIndex, cardIndex);
        while (!isTrueGetting) {
            System.out.println("\u001b[34;m Какую сумму Вы хотите получить?\n");
            System.out.println("\u001b[31;m снятие возможно на сумму, кратную 100 руб.\n");
            Scanner scMoney = new Scanner(System.in);
            try {
                int money = scMoney.nextInt();
                if (money % 100 == 0) {
                    if (balance > money) {
                        acc.setCardBalance(clientIndex, cardIndex, -money);
                        System.out.println("\u001b[34;m Снятие " + money + " рублей.\n");
                        isTrueGetting = true;
                    } else
                        throw new NoMoneyOnCardException("\u001b[31;m Недостаточно средств на карте!", balance, money);
                } else {
                    System.out.println("\u001b[31;m снятие возможно на сумму, кратную 100 руб.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001b[31;m Пожалуйста, укажите корректную сумму!");
            }
        }
    }

    @Override
    public void createClient() {
        boolean isTruePin = false;
        boolean isTrueLogin = false;
        String login = null;
        String pin = null;
        while (!isTrueLogin) {
            try {
                login = acc.enterLogin();
                if (acc.checkDuplicateLogin(login) == 1) {
                    isTrueLogin = true;
                }
            } catch (ClientDuplicatedException clDe) {
                System.out.println(clDe.getMessage());
                clDe.getDuplicatedLogin();
            }
        }
        while (!isTruePin) {
            try {
                pin = acc.enterPin();
                isTruePin = true;
            } catch (IncorrectPinException iPe) {
                System.out.println(iPe.getMessage());
                iPe.getIncorrectPinFormatMessage();
            }
        }
        acc.createAccount(login, pin);
    }

    @Override
    public void deleteClient(int clientIndex) {
        acc.deleteClientFromAccount(clientIndex);
        System.out.println("\u001b[31;m Ваш аккаунт был удален!\n");
    }

    @Override
    public String createCard(int clientIndex) {
        boolean isTrueCard = false;
        String cardNumber = null;
        while (!isTrueCard) {
            try {
                cardNumber = acc.enterCardNumber();
                if (acc.checkValidCardNumber(cardNumber) == -1) {
                    acc.setCardNumber(cardNumber, clientIndex);
                    isTrueCard = true;
                }
            } catch (IncorrectCardNumberException iCnE) {
                System.out.println(iCnE.getMessage());
                iCnE.getIncorrectNumberFormatMessage();
            } catch (CardDuplicatedException caDe) {
                System.out.println(caDe.getMessage());
                caDe.getDuplicatedNumber();
            }
        }
        return cardNumber;
    }

    @Override
    public void deleteCard(int clientIndex, int cardIndex) {
        acc.deleteCardFromAccount(clientIndex, cardIndex);
        System.out.println("\u001b[31;m Ваша карта была удалена!\n");
    }

    void selectCard(int clientIndex) throws NoMoneyOnCardException {
        boolean isTrueCard = false;
        while (!isTrueCard) {
            if (acc.cardsViewer(clientIndex) == 1) {
                Scanner scWhichCard = new Scanner(System.in);
                int whichCard = scWhichCard.nextInt();
                if (whichCard > 0 && whichCard <= acc.getCardsSize(clientIndex)) {
                    menu2Level(whichCard - 1);
                    isTrueCard = true;
                }
            } else {
                System.out.println("\u001b[31;m У Вас еще нет карт!\n");
                isTrueCard = true;
            }
        }
    }
}
