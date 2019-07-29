package ru.job4j.carwarehouse.service;

import org.apache.log4j.Logger;
import ru.job4j.carwarehouse.entity.User;
import ru.job4j.carwarehouse.store.UserStore;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class ValidateUser implements Validate {

    private static final ValidateUser INSTANCE = new ValidateUser();

    private final UserStore userStore = UserStore.getInstance();

    private static final Logger LOGGER = Logger.getLogger(ValidateUser.class);

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public static ValidateUser getInstance() {
        return INSTANCE;
    }

    private ValidateUser() {
    }

    @Override
    public boolean add(User user) {
        int result = 0;
        try {
            if (
                    user.getLogin() != null
                            && user.getEmail() != null
                            && !isLoginEmailExist(user.getLogin(), user.getEmail())
                            && emailValidation(user.getEmail())
                    ) {
                result = userStore.add(user);
            }
        } catch (UserException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result > 0;
    }


    @Override
    public boolean update(User user) {
        int result = 0;
        try {
            if (user.getLogin() != null
                    && user.getEmail() != null
                    && !isLoginEmailExistForUpdate(user.getId(), user.getLogin(), user.getEmail())
                    && emailValidation(user.getEmail())) {
                result = userStore.update(user);
            }
        } catch (UserException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result > 0;
    }

    @Override
    public boolean delete(int id) {
        int result = userStore.delete(id);
        return result > 0;
    }

    @Override
    public List findAll() {
        return userStore.getAll();
    }

    @Override
    public User findById(int id) {
        return userStore.getById(id);
    }

    private boolean emailValidation(String email) throws UserException {
        Matcher matcher = PATTERN.matcher(email);
        boolean result = true;
        if (!matcher.matches()) {
            throw new UserException("Enter valid email address");
        }
        return result;
    }

    private boolean isLoginEmailExist(String login, String email) throws UserException {
        boolean result = false;
        List<User> list = userStore.getAll();
        checkList(list, login, email);
        return result;
    }


    private boolean isLoginEmailExistForUpdate(int id, String login, String email) throws UserException {
        boolean result = false;
        User user = userStore.getById(id);
        if (user != null) {
            List<User> list = userStore.getAll();
            list.remove(user);
            checkList(list, login, email);
        }

        return result;
    }

    private void checkList(List<User> list, String login, String email) throws UserException {
        for (User u : list) {
            if (u.getLogin().equals(login)) {
                throw new UserException("User with login:" + login + " already exists.");
            }
            if (u.getEmail().equals(email)) {
                throw new UserException("User with email:" + email + " already exists.");
            }
        }
    }
}