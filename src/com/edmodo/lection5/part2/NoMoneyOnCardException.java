package com.edmodo.lection5.part2;

import static com.edmodo.lection5.part2.ATM.restMoney;

/**
 * Created by pc on 28.11.2016.
 */
public class NoMoneyOnCardException extends Exception {
    private int needMoney;

    void getNeedMoney() {
        System.out.println("\u001b[32;m Для совершения этой операции Вам необходимо пополнить счет на "
                + "\u001b[31;m" + needMoney + "\u001b[32;m рублей\n");
    }

    public NoMoneyOnCardException(String message, int sum) {

        super(message);
        needMoney = sum - restMoney;
    }

}
