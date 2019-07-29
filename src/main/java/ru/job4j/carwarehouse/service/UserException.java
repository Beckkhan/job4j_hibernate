package ru.job4j.carwarehouse.service;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class UserException extends Exception {

    public UserException(String errorMessage) {
        super(errorMessage);
    }
}