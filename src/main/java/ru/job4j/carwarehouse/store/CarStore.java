package ru.job4j.carwarehouse.store;

import ru.job4j.carwarehouse.entity.*;

import java.util.ArrayList;
import java.util.List;
import static ru.job4j.carwarehouse.store.Wrapper.tx;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class CarStore implements Store<Car> {

    private static final CarStore INSTANCE = new CarStore();

    private final BodytypeStore bodytypeStore = BodytypeStore.getInstance();

    private final EngineStore engineStore = EngineStore.getInstance();

    private final TransmissionStore transmissionStore = TransmissionStore.getInstance();

    public static CarStore getInstance() {
        return INSTANCE;
    }

    private CarStore() {
    }

    @Override
    public int add(Car car) {
        return tx(session ->  {
            session.saveOrUpdate(car);
            return car.getId();
        });
    }

    @Override
    public int update(Car car) {
        return tx(session -> {
            session.update(car); return car.getId();
        });
    }

    @Override
    public int delete(int id) {
        return tx(session -> {
            Car car = session.get(Car.class, id);
            session.delete(car);
            return car.getId();
        });
    }

    @Override
    public List<Car> getAll() {
        return tx(session -> session.createQuery("from Car ", Car.class).list());
    }

    @Override
    public Car getById(int id) {
        return tx(session -> session.get(Car.class, id));
    }

    @Override
    public Car getByName(String carBrand) {
        Car car;
        try {
            car = tx(session ->
                    session.createQuery("select C from Car C where C.brand = : brand", Car.class)
                            .setParameter("brand", carBrand).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return car;
    }

    public Car constructCar(Car car, String bodyName, String engineName, String transmissionName) {
        Bodytype bodytype = bodytypeStore.getByName(bodyName);
        if (bodytype == null) {
            bodytype = new Bodytype(bodyName);
            bodytypeStore.add(bodytype);
        }
        car.setBodytype(bodytype);

        Engine engine = engineStore.getByName(engineName);
        if (engine == null) {
            engine = new Engine(engineName);
            engineStore.add(engine);
        }
        car.setEngine(engine);

        Transmission transmission = transmissionStore.getByName(transmissionName);
        if (transmission == null) {
            transmission = new Transmission(transmissionName);
            transmissionStore.add(transmission);
        }
        car.setTransmission(transmission);

        return car;
    }

    public List<String> getLocation() {
        return tx(session -> session.createQuery("select distinct C.location from Car C").list());
    }

    public void statusChange(int id, boolean done) {
        tx(session -> {
            Car car = session.get(Car.class, id);
            car.setSold(done);
            return null;
        });
    }

    public List<Car> filterCarsBySold(boolean sold) {
        return tx(session -> session.createQuery("select C from Car C where C.sold = : sold", Car.class)
                .setParameter("sold", sold).getResultList());
    }

    public List<Car> filterCarsBySoldAndName(boolean sold, String name) {
        final String parameter = name.toLowerCase();
        return tx(session ->
                session.createQuery("select C from Car C where C.sold = : sold and lower(C.name) = : name", Car.class)
                        .setParameter("sold", sold)
                        .setParameter("name", parameter)
                        .getResultList());
    }

    public List<Car> filterCarsByName(String name) {
        //final String parameter = name.toLowerCase();
        List<Car> allList = this.getAll();
        /*List<Car> allList = tx(session -> session.createQuery("select C from Car C where lower(C.brand) = : name", Car.class)
                .setParameter("name", parameter).getResultList());*/
        List<Car> result = new ArrayList<>();
        for (Car car: allList) {
            if (car.getBrand().compareToIgnoreCase(name) == 0
                    || car.getBrand().toLowerCase().contains(name.toLowerCase())) {
                result.add(car);
            }
        }
        return result;
    }
}