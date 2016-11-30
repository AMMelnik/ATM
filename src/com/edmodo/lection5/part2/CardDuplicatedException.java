package com.edmodo.lection5.part2;

/**
 * Created by pc on 29.11.2016.
 */
public class CardDuplicatedException extends Exception {
    private String duplicatedNumber;

    void getDuplicatedNumber() {
        System.out.println("\u001b[32;m Для совершения этой операции Вам необходимо изменить номер карты на неиспользуемый, вместо номера: \n"
                + "\u001b[31;m " + duplicatedNumber + "\n");
    }

    CardDuplicatedException(String message, String num) {
        super(message);
        duplicatedNumber = num;
    }
}
