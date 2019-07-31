package ru.job4j.carwarehouse.store;

import org.junit.Test;
import ru.job4j.carwarehouse.entity.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 31.07.2019
 */
public class CarStoreTest {

    private final CarStore store = CarStore.getInstance();

    @Test
    public void whenAddCarAndGetById() {
        Car car = new Car("Genesis");
        int id = store.add(car);
        assertThat(id, is(store.getById(id).getId()));
    }

    @Test
    public void whenAddThenUpdateCarAndGetLocation() {
        Car car = new Car("Hyundai");
        car.setLocation("Saint-Petersburg");
        store.add(car);
        Car dbCar = store.getByName("Hyundai");
        dbCar.setLocation("Moscow");
        store.update(dbCar);
        assertThat("Moscow", is(store.getByName("Hyundai").getLocation()));
    }

    @Test
    public void whenConstructThenAddCar() {
        Car car = new Car("BMW");
        car = store.constructCar(car, "sedan", "gasoline", "auto");
        store.add(car);
        assertThat(car.getEngine().getName(), is(store.getByName("BMW").getEngine().getName()));
    }

    @Test
    public void whenAddCarAndGetLocation() {
        Car car = new Car("Toyota");
        car.setLocation("Saint-Petersburg");
        store.add(car);
        assertThat("Saint-Petersburg", is(store.getByName("Toyota").getLocation()));
    }

    @Test
    public void whenAddCarThenChangeStatus() {
        Car car = new Car("Skoda");
        car.setSold(false);
        store.add(car);
        Car dbCar = store.getByName("Skoda");
        store.statusChange(dbCar.getId(), true);
        assertThat(true, is(store.getByName("Skoda").isSold()));
    }

    @Test
    public void whenAddThenDeleteCar() {
        Car car = new Car("Nissan");
        int id = store.add(car);
        assertThat(id, is(store.delete(id)));
    }
}