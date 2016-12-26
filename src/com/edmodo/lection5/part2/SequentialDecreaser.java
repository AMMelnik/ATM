package com.edmodo.lection5.part2;

import java.io.IOException;

/**
 * Created by pc on 24.12.2016.
 */
class SequentialDecreaser implements Runnable {

    private ATM atmGetMoney = new ATM();
    private int cyclesNum;

    SequentialDecreaser(ATM atm, int cyclesNum) {
        atmGetMoney = atm;
        this.cyclesNum = cyclesNum;
    }

    @Override
    public void run() {
        for (int i = 0; i < cyclesNum; i++) {
            atmGetMoney.getMoneyBySequentialDecreaser();
        }
    }
}