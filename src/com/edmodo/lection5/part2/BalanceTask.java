package com.edmodo.lection5.part2;

/**
 * Created by pc on 28.12.2016.
 */
class BalanceTask implements Runnable{

    private SynchronizedAccount syncAcc;

    BalanceTask(ATM atm) {
        syncAcc = new SynchronizedAccount(atm);
    }

    @Override
    public void run() {
        int sum = (int) (Math.random() * 5000) + 150;
        syncAcc.withdraw();
        syncAcc.refill();
    }
}
