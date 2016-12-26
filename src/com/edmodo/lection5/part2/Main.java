package com.edmodo.lection5.part2;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by pc on 28.11.2016.
 */
class Main {

    public static void main(String[] args) {
        System.out.println("\u001b[34;m Добро пожаловать в Терминал!\n");
        ATM atm = new ATM();
        try {
            atm.deSerialAccount();
        } catch (IOException e) {
            System.out.println("\u001b[31;m Файл с данными аккаунтов будет создан по завершению работы\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        terminalMenu(atm);
    }

    private static void terminalMenu(ATM atm) {
        boolean terminalEnd = false;
        while (!terminalEnd) {
            System.out.println("\u001b[34;m Выберите действие:\n");
            System.out.println("\u001b[32;m (1) Создать аккаунт\n" + " (2) Авторизоваться\n" +
                    " (3) Запустить Нити Increaser & Decreaser\n" +
                    " (4) Запустить SequentialIncreaser & SequentialDecreaser\n" + " (5) Выйти из терминала\n");
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
                    Increaser increaser = new Increaser();
                    Decreaser decreaser = new Decreaser();
                    increaser.setPriority(Thread.MAX_PRIORITY);
                    decreaser.setPriority(Thread.MIN_PRIORITY);
                    increaser.start();
                    increaser.interrupt();
                    decreaser.start();
                    decreaser.interrupt();
                    break;
                case "4":
                    SecventialIncreaser secventialIncreaser = new SecventialIncreaser(atm);
                    SequentialDecreaser sequentialDecreaser = new SequentialDecreaser(atm);
                    Thread sIThred = new Thread(secventialIncreaser);
                    sIThred.setPriority(Thread.NORM_PRIORITY);
                    sIThred.start();
                    Thread sDThread = new Thread(sequentialDecreaser);
                    sDThread.setPriority(Thread.NORM_PRIORITY);
                    sDThread.start();
                    break;
                case "5":
                    try {
                        atm.serialAccount();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
