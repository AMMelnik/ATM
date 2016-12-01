package com.edmodo.lection5.part2;

import java.util.Scanner;

/**
 * Created by pc on 28.11.2016.
 */
class Main {

    public static void main(String[] args) throws NoMoneyOnCardException {
        System.out.println("\u001b[34;m Добро пожаловать в Терминал!\n");
        ATM atm = new ATM();
        terminalMenu(atm);
    }

    static void terminalMenu(ATM atm) throws NoMoneyOnCardException {
        boolean terminalEnd = false;
        while (!terminalEnd) {
            System.out.println("\u001b[34;m Выберите действие:\n");
            System.out.println("\u001b[32;m (1) Создать аккаунт\n" + " (2) Авторизоваться\n" + " (3) Выйти из терминала\n");
            Scanner scEntryType = new Scanner(System.in);
            String entryType = scEntryType.nextLine();
            switch (entryType) {
                case "1":
                    atm.createClient();
                    break;
                case "2":
                    atm.authorization();
                    break;
                case "3":
                    System.out.println("\u001b[30;m Всего доброго!");
                    terminalEnd = true;
                    break;
                default:
                    System.out.println("\u001b[34;m Пожалуйста, выберите нужное действие (1 - 3)\n");
                    break;
            }
        }
    }
}
