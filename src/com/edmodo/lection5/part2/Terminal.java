package com.edmodo.lection5.part2;


/**
 * Created by pc on 28.11.2016.
 */
public interface Terminal {

    int checkAccountStatus();

    int refillAccount();

    int getMoney() throws NoMoneyOnCardException;

    void createClient();

    void deleteClient();

    void createCard();

    void deleteCard();
}
