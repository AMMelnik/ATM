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
                    " (4) Запустить SequentialIncreaser & SequentialDecreaser\n" +
                    " (5) Запустить работу с SynchronizedAccount\n" + " (6) Выйти из терминала\n");
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
                    int numT = (int) (Math.random() * 10) + 1;
                    Increaser increaser = new Increaser(atm, numT);
                    Decreaser decreaser = new Decreaser(atm, numT);
                    // зависимость от приоритета
                    increaser.setPriority(Thread.MAX_PRIORITY);
                    increaser.setName("Нить Increaser");
                    // зависимость от приоритета
                    decreaser.setPriority(Thread.MIN_PRIORITY);
                    decreaser.setName("Нить Decreaser");
                    increaser.start();
                    increaser.interrupt();
                    decreaser.start();
                    decreaser.interrupt();
                    break;
                case "4":
                    int numR = (int) (Math.random() * 5) + 1;
                    SequentialIncreaser sequentialIncreaser = new SequentialIncreaser(atm, numR);
                    SequentialDecreaser sequentialDecreaser = new SequentialDecreaser(atm, numR);
                    Thread sIThred = new Thread(sequentialIncreaser);
                    sIThred.setName("Нить Sequential_Increaser");
                    // независимость от приоритета
                    sIThred.setPriority(Thread.MAX_PRIORITY);
                    sIThred.start();
                    Thread sDThread = new Thread(sequentialDecreaser);
                    sDThread.setName("Нить Sequential_Decreaser");
                    // независимость от приоритета
                    sDThread.setPriority(Thread.MIN_PRIORITY);
                    sDThread.start();
                    break;
                case "5":
                    boolean threadNumEnter = false;
                    int threadNum = 1;
                    while (!threadNumEnter) {
                        System.out.println("\u001b[34;m Сколько потоков создать?\n");
                        Scanner scThreadNum = new Scanner(System.in);
                        try {
                            threadNum = scThreadNum.nextInt();
                        } catch (Exception e) {
                            System.out.println("\u001b[31;m Пожалуйста, введите целое положительное число!\n");
                        }
                        for (int i = 1; i <= threadNum; i++) {
                            Thread t = new Thread(new BalanceTask(atm));
                            t.setName("Поток " + i);
                            t.start();
                        }
                        threadNumEnter = true;
                    }
                    break;
                case "6":
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
