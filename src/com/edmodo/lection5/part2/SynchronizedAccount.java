package com.edmodo.lection5.part2;


/**
 * Created by pc on 27.12.2016.
 */
class SynchronizedAccount {

    private Account safeAccount = new Account();
    // private final Object accountLock = new Object();
    private int balance;

    SynchronizedAccount(ATM atm) {
        safeAccount = atm.acc;
    }

    private synchronized int getClientsSize() {
        return safeAccount.getAccount().size();
    }

    private synchronized int getCardsSize(int clientIndex) {
        return safeAccount.getCardsSize(clientIndex);
    }

    private synchronized int getBalance(int clientIndex, int cardIndex) {
        return balance = safeAccount.getCardBalance(clientIndex, cardIndex);
    }

    private synchronized String getClientName(int clientIndex) {
        return safeAccount.getClientName(clientIndex);
    }

    private synchronized String getClientCard(int clientIndex, int cardIndex) {
        return safeAccount.getCardNumber(clientIndex, cardIndex);
    }

    private synchronized void withdraw(int clientIndex, int cardIndex, int amount) {
        if (balance >= amount) {
            safeAccount.setCardBalance(clientIndex, cardIndex, -amount);
            System.out.println("\u001b[34;m Снятие средств в размере " + amount + " руб.\n");
        } else {
            System.out.println("\u001b[31;m Снятие средств в размере " + amount +
                    " руб. нельзя совершить из-за недостатка средств на счете!\n");
        }
        getBalance(clientIndex, cardIndex);

    }

    private synchronized void refill(int clientIndex, int cardIndex, int amount) {
        safeAccount.setCardBalance(clientIndex, cardIndex, amount);
        System.out.println("\u001b[34;m Пополнение средств в размере " + amount + " руб.\n");
        getBalance(clientIndex, cardIndex);
    }

    private synchronized String showClientInfo(int clientIndex, int cardIndex) {
        return "\u001b[34;m Информация по клиенту:\n Логин: " + getClientName(clientIndex) +
                "\n Карта: " + getClientCard(clientIndex, cardIndex) + "\n Баланс: " +
                balance + " \n";

    }

    private synchronized void showBalanceInfo(int clientIndex, int cardIndex) {
        System.out.println("\u001b[34;m Баланс: " + getBalance(clientIndex, cardIndex) + " \n");
    }

    synchronized void safeThreadWork() {
        int sum, transactType;
        for (int i = 0; i < getClientsSize(); i++) {
            for (int j = 0; j < getCardsSize(i); j++) {
                synchronized (this) {
                    transactType = (int) (Math.random() * 2);
                    sum = (int) (Math.random() * 5000) + 150;
                    getBalance(i, j);
                    System.out.println("\u001b[32;m " + Thread.currentThread().getName() + "\n" + showClientInfo(i, j));
                    switch (transactType) {
                        case 0:
                            withdraw(i, j, sum);
                            break;
                        case 1:
                            refill(i, j, sum);
                            break;
                    }
                    showBalanceInfo(i, j);
                    notifyAll();
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

