package com.edmodo.lection5.part2;

import java.io.IOException;

/**
 * Created by pc on 24.12.2016.
 */
public class SequentialDecreaser implements Runnable {
    private ATM atmGetMoney = new ATM();

    SequentialDecreaser(ATM atm) {
        atmGetMoney = atm;
    }

    @Override
    public void run() {
        atmGetMoney.getMoneyBySequentialDecreaser();
    /*    try {
            atmGetMoney.serialAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}