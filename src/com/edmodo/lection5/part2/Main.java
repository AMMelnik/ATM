package com.edmodo.lection5.part2;

import java.util.Scanner;

import static com.edmodo.lection5.part2.ATM.restMoney;

/**
 * Created by pc on 28.11.2016.
 */
class Main {

    public static void main(String[] args) throws NoMoneyOnCardException {
        boolean terminalEnd = false;
        System.out.println("\u001b[34;m Добро пожаловать в Терминал!\n");
        ATM atm = new ATM();
        while (!terminalEnd) {
            System.out.println("\u001b[32;m (1) Создать аккаунт\n" + " (2) Авторизоваться\n");
            Scanner scEntryType = new Scanner(System.in);
            int entryType = scEntryType.nextInt();
            if (entryType == 1) {
                atm.createClient();
            }
            if (entryType == 2) {
                atm.authorization();

            }
            System.out.println("\u001b[30;m Что делать дальше?");
            StringBuilder strAction = new StringBuilder("\u001b[32;m (1) Выйти из терминала\n")
                    .append("\u001b[32;m (2) Вернуться на главную страницу\n");
            System.out.println(strAction);
            Scanner scAction = new Scanner(System.in);
            int actionType = scAction.nextInt();
            if (actionType == 1) {
                System.out.println("\u001b[30;m Всего доброго!");
                terminalEnd = true;

            }
        }


    }
}
