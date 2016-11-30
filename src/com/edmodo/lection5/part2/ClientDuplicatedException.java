package com.edmodo.lection5.part2;

/**
 * Created by pc on 28.11.2016.
 */
public class ClientDuplicatedException extends Exception {

    private String duplicatedLogin;
    void getDuplicatedLogin() {
        System.out.println("\u001b[32;m Для совершения этой операции Вам необходимо изменить логин клиента на неиспользуемый, вместо логина:\n "
                + "\u001b[31;m" + duplicatedLogin + "\n");
    }

    public ClientDuplicatedException(String message, String login) {

        super(message);
        duplicatedLogin = login;
    }
}
