package com.edmodo.lection5.part2;


/**
 * Created by pc on 28.11.2016.
 */
interface Terminal {

    int checkAccountStatus();

    int addMoney();

    int getMoney() throws NoMoneyOnCardException;

    void createClient();

    void deleteClient();

    void createCard();

    void deleteCard();
}
