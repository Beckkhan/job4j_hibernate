package ru.job4j.carwarehouse.store;

import org.junit.Test;
import ru.job4j.carwarehouse.entity.User;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 31.07.2019
 */
public class UserStoreTest {

    private final UserStore store = UserStore.getInstance();

    @Test
    public void whenCheckThatUserPresent() {
        User user = new User("First", "first", "first@gmail.com", "first");
        store.add(user);
        assertThat("First", is(store.loginPermit("first", "first").getName()));
    }

    @Test
    public void whenDeleteUser() {
        User user = new User("Second", "second", "second@gmail.com", "second");
        int id = store.add(user);
        assertThat(id, is(store.delete(id)));
    }

    @Test
    public void whenGetUserByLogin() {
        User user = new User("Third", "thirdlogin", "third@gmail.com", "password");
        store.add(user);
        assertThat("thirdlogin", is(store.getByName("thirdlogin").getLogin()));
    }

    @Test
    public void whenGetUserById() {
        User user = new User("Fourth", "fourth", "fourth@gmail.com", "fourth");
        int id = store.add(user);
        assertThat("Fourth", is(store.getById(id).getName()));
    }

    @Test
    public void whenUpdateUser() {
        User user = new User("Fifth", "Fifth", "fifth@gmail.com", "password");
        int id = store.add(user);
        user = store.getById(id);
        user.setPassword("newpassword");
        store.update(user);
        assertThat("newpassword", is(store.getById(id).getPassword()));
    }
}