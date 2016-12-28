package com.edmodo.lection5.part2;

/**
 * Created by pc on 28.12.2016.
 */
class BalanceTask implements Runnable {

    private SynchronizedAccount syncAcc;

    BalanceTask(ATM atm) {
        syncAcc = new SynchronizedAccount(atm);
    }

    @Override
    public void run() {
        int sum, transactType;
        for (int i = 0; i < syncAcc.getClientsSize(); i++) {
            for (int j = 0; j < syncAcc.getCardsSize(i); j++) {
                synchronized (this) {
                    transactType = (int) (Math.random() * 2);
                    sum = (int) (Math.random() * 5000) + 150;
                    System.out.println("\u001b[32;m " + Thread.currentThread().getName());
                    syncAcc.showClientInfo(i, j);
                    switch (transactType) {
                        case 0:
                            syncAcc.withdraw(i, j, sum);
                            break;
                        case 1:
                            syncAcc.refill(i, j, sum);
                            break;
                    }
                    notifyAll();
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
