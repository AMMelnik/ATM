package com.edmodo.lection5.part2;

/**
 * Created by pc on 24.12.2016.
 */
class Decreaser extends Thread {

    private ATM atmGetMoney = new ATM();
    private int cyclesNum;

    Decreaser(ATM atm, int cyclesNum) {
        atmGetMoney = atm;
        this.cyclesNum = cyclesNum;
    }

    @Override
    public void run() {
        for (int i = 0; i < cyclesNum; i++) {
            atmGetMoney.getMoneyByDecreaser();
        }
    }
}
