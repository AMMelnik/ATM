package com.edmodo.lection5.part2;

/**
 * Created by pc on 28.11.2016.
 */
public class IncorrectPinException extends Exception {

    void getIncorrectPinFormatMessage() {
        System.out.println("\u001b[32;m Для задания ПИН-кода введите четыре числа от 0 до 9 без пробелов\n");
    }

    IncorrectPinException(String message) {

        super(message);
    }
}
