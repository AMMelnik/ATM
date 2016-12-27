package com.edmodo.lection5.part2;


/**
 * Created by pc on 27.12.2016.
 */
class SynchronizedAccount {

    private volatile Account safeAccount = new Account();
    private volatile int balanse;
    private volatile String name;
    private volatile String card;

    SynchronizedAccount(ATM atm) {
        safeAccount = atm.acc;
    }


    synchronized int getBalance(int clientIndex, int cardIndex) {
        balanse = safeAccount.getCardBalance(clientIndex, cardIndex);
        return balanse;
    }

    synchronized String getClientName(int clientIndex) {
        name = safeAccount.getClientName(clientIndex);
        return name;
    }

    synchronized String getClientCard(int clientIndex, int cardIndex) {
        card = safeAccount.getCardNumber(clientIndex, cardIndex);
        return card;
    }

    synchronized void withdraw(int clientIndex, int cardIndex, int amount) {
        if (getBalance(clientIndex, cardIndex) > amount) {
            safeAccount.setCardBalance(clientIndex, cardIndex, balanse - amount);
            System.out.println("\u001b[34;m Снятие средств в размере" + amount + " руб.\n");
        } else {
            System.out.println("\u001b[31;m Снятие средств в размере" + amount +
                    " руб. нельзя совершить из-за недостатка средств на счете!\n");
        }
    }

    synchronized void refill(int clientIndex, int cardIndex, int amount) {
        safeAccount.setCardBalance(clientIndex, cardIndex, balanse + amount);
        System.out.println("\u001b[34;m Пополнение средств в размере" + amount + " руб.\n");
    }

    synchronized void showClientInfo(int clientIndex, int cardIndex) {
        System.out.println("\u001b[34;m Информация по клиенту:\n Логин: " + getClientName(clientIndex) +
                "\n Карта: " + getClientCard(clientIndex, cardIndex) + "\n Баланс: " +
                getBalance(clientIndex, cardIndex) + " \n");
    }


}

