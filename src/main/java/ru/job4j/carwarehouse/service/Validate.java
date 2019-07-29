package ru.job4j.carwarehouse.service;

import ru.job4j.carwarehouse.entity.User;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public interface Validate {

    boolean add(User user);

    boolean update(User user);

    boolean delete(int id);

    List findAll();

    User findById(int id);
}