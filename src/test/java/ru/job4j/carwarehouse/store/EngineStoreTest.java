package ru.job4j.carwarehouse.store;

import org.junit.Test;
import ru.job4j.carwarehouse.entity.Engine;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 31.07.2019
 */
public class EngineStoreTest {

    private final EngineStore store = EngineStore.getInstance();

    @Test
    public void whenAddThenDeleteEngine() {
        Engine gasoline = new Engine("gasoline");
        int id = store.add(gasoline);
        assertThat(id, is(store.delete(id)));
    }

    @Test
    public void whenGetEngineById() {
        Engine diesel = new Engine("diesel");
        int id = store.add(diesel);
        assertThat("diesel", is(store.getById(id).getName()));
    }

    @Test
    public void whenGetAllTypes() {
        Engine gasoline = new Engine("gasoline");
        Engine hydrogen = new Engine("hydrogen");
        Engine gas = new Engine("gas");
        Engine electric = new Engine("electric");
        store.add(gasoline);
        store.add(hydrogen);
        store.add(gas);
        store.add(electric);
        assertThat(store.getEngineTypes().size(), is(5));
    }
}