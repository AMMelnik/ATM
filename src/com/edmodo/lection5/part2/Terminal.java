package com.edmodo.lection5.part2;


/**
 * Created by pc on 28.11.2016.
 */
interface Terminal {

    void checkAccountStatus(int clientIndex, int cardIndex);

    void addMoney(int clientIndex, int cardIndex);

    void getMoney(int clientIndex, int cardIndex) throws NoMoneyOnCardException;

    void createClient();

    void deleteClient(int clientIndex);

    String createCard(int clientIndex);

    void deleteCard(int clientIndex, int cardIndex);
}
