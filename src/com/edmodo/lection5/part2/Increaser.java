package com.edmodo.lection5.part2;

import java.io.IOException;

/**
 * Created by pc on 24.12.2016.
 */
public class Increaser extends Thread {

    private ATM atmAddMoney = new ATM();

    @Override
    public void run() {
        try {
            atmAddMoney.deSerialAccount();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        atmAddMoney.addMoneyByIncreaser();
        try {
            atmAddMoney.serialAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
