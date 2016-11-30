package com.edmodo.lection5.part2;

/**
 * Created by pc on 30.11.2016.
 */
public class IncorrectCardChoiceException extends Exception {

    void getIncorrectCardChoiceMessage() {
        System.out.println("\u001b[32;m Пожалуйста, выберите карту из списка!\n");
    }

    IncorrectCardChoiceException(String message) {
        super(message);
    }
}
