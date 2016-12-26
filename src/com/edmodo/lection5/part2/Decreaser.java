package com.edmodo.lection5.part2;

import java.io.IOException;

/**
 * Created by pc on 24.12.2016.
 */
public class Decreaser extends Thread {

    private ATM atmGetMoney = new ATM();

    @Override
    public void run() {
        try {
            atmGetMoney.deSerialAccount();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        atmGetMoney.getMoneyByDecreaser();
        try {
            atmGetMoney.serialAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
      /* try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
