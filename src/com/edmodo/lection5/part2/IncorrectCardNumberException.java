package com.edmodo.lection5.part2;

/**
 * Created by pc on 28.11.2016.
 */
class IncorrectCardNumberException extends Exception {

    void getIncorrectNumberFormatMessage() {
        System.out.println("\u001b[32;m Для совершения этой операции Вам необходимо задать номер карты в формате XXXX XXXX XXXX XXXX\n");
    }

    IncorrectCardNumberException(String message) {
        super(message);
    }
}
