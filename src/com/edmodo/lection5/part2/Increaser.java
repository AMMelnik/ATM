package com.edmodo.lection5.part2;


/**
 * Created by pc on 24.12.2016.
 */
class Increaser extends Thread {

    private ATM atmAddMoney = new ATM();
    private int cyclesNum;

    Increaser(ATM atm, int cyclesNum) {
        atmAddMoney = atm;
        this.cyclesNum = cyclesNum;
    }

    @Override
    public void run() {
        for (int i = 0; i < cyclesNum; i++) {
            atmAddMoney.addMoneyByIncreaser();
        }
    }
}
