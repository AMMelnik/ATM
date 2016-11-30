package com.edmodo.lection5.part2;

import java.util.LinkedList;

/**
 * Created by pc on 30.11.2016.
 */
class Account {
    LinkedList<String> account;
    void createAccount() {
        account = new LinkedList<>();
    }

    void setAccount(String data) {
        if (!account.contains(data)) {
            account.add(data);
        } else {
            System.out.println("\u001b[31;m Эта карта уже добавлена!\n");
        }
    }
    @Override
    public String toString() {
        return account.toString();
    }
}
