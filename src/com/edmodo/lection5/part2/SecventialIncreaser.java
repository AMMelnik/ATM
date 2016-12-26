package com.edmodo.lection5.part2;

import java.io.IOException;

/**
 * Created by pc on 24.12.2016.
 */
class SecventialIncreaser implements Runnable {

    private ATM atmAddMoney = new ATM();
    private int cyclesNum;

    SecventialIncreaser(ATM atm, int cyclesNum) {
        atmAddMoney = atm;
        this.cyclesNum = cyclesNum;
    }

    @Override
    public void run() {
        for (int i = 0; i < cyclesNum; i++) {
            atmAddMoney.addMoneyBySequentialIncreaser();
        }
    }
}
