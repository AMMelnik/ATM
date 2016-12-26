package com.edmodo.lection5.part2;

import java.io.IOException;

/**
 * Created by pc on 24.12.2016.
 */
public class SecventialIncreaser implements Runnable {

    private ATM atmAddMoney = new ATM();

    SecventialIncreaser(ATM atm) {
        atmAddMoney = atm;
    }

    @Override
    public void run() {
        atmAddMoney.addMoneyBySequentialIncreaser();
    /*    try {
            atmAddMoney.serialAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
