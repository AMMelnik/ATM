package com.edmodo.lection5.part2;


/**
 * Created by pc on 28.11.2016.
 */
interface Terminal {

    void checkAccountStatus(int cardIndex);

    void addMoney(int cardIndex);

    void getMoney(int cardIndex) throws NoMoneyOnCardException;

    void createClient();

    void deleteClient();

    void createCard();

    void deleteCard();
}
